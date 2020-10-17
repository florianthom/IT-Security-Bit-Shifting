/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itsecruity;

import itsecruity.config.BigIntConfiguration;
import itsecruity.enums.OutputFormat;
import itsecruity.enums.Tuple;
import itsecruity.enums.signt;
import itsecruity.enums.signt2;
import itsecruity.exceptions.SizeOverflowException;

import java.lang.Math; 


/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class BigNumber extends BigIntConfiguration implements Cloneable {
	
	// lenth of this.value
	// number of cells
    protected short size;    
    
	// "Es wird der signifikante Teil auf die Länge newSize Zellen gebracht."
	// length of significant part in cells // glaube in zellen, nicht in bits gerechnet
    public short spart;
    
    // sign
    protected signt sign;
    
    // array itself
    // cell array
    public int value[];
    
    public int numberOfInitBits;
    

    
    
    
    
    public BigNumber() {
        this((short)0);
    }

    public BigNumber(String str) {
        this((short)0);
        toBigInt(str);
    }
    
    public BigNumber(short val) {
        this(ARRAY_BIT_SIZE_STATIC,val);
    }
    
    public BigNumber(short bitsize, String str) {
        this(bitsize,(short)0);
        toBigInt(str);
    }

    

    public BigNumber(short numberBitsInTotalForWholeArray, short val)
    {
    	this.size = (short) (numberBitsInTotalForWholeArray / CELL_SIZE);
    	this.numberOfInitBits = numberBitsInTotalForWholeArray;
    	this.value = new int[this.size];
    	this.sign = signt.positive;
    	if(this.value.length==0)
    	{
    		System.out.println("numberBitsInTotalForWholeArray: " + numberBitsInTotalForWholeArray);
    	}
    	this.value[0] = val;
		this.spart=1;
    }
    

    public BigNumber(BigNumber tmp)
    {
    	this.size = tmp.size;
    	this.spart = tmp.spart;
    	this.sign = tmp.sign;
    	this.numberOfInitBits = tmp.numberOfInitBits;
    	this.value = tmp.value.clone();
    }
    
    public BigNumber(BigNumber tmp, signt s)
    {
    	this.size = tmp.size;
    	this.spart = tmp.spart;
    	this.sign = s;
    	this.value = tmp.value.clone();
    }
    
    public BigNumber(int number)
    {
    	this(Integer.toString(number));
    }
    
    public BigNumber(short a, int number)
    {
    	this(a, Integer.toString(number));
    }
    
    
    
    
    
    
    
    
    public void setNewValue(BigNumber tmp)
    {
    	this.size = tmp.size;
    	this.spart = tmp.spart;
    	this.sign = tmp.sign;
    	this.value = tmp.value.clone();
    }
    
    
    public static void reduce(BigNumber a)
    {
    	int indexHighesCell = 0;
    	for(int i = a.value.length-1; i>=0; i--)
    	{
    		if(a.value[i]!=0)
    		{
    			indexHighesCell = i;
    			break;
    		}
    	}
    	a.spart = (short) (indexHighesCell+1);
    }
       
    
 
    public void assign(BigNumber a) {
        
    }
    
    
    protected void correctNegativeZero() {
		this.sign = signt.positive;

    }

    
    @Override
    public BigNumber clone() throws CloneNotSupportedException {
        BigNumber tmp = null;
        try {
            tmp = (BigNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException();
        }
        return tmp;
    }


    // toString() defaults to a hex-string (and not a string with decimal-value)
    @Override
    public String toString() {
        try {
			return toString16();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    
    
    public String toStringSign() {
        if (sign == signt.negative) {
            return "-";
        }
        return "+";
    }

    public static String repeatStringXTimes(String tmp, int times)
    {
    	return tmp.repeat(times);
    }
    
    public static void reduceArrayIfpossibleToInitBitSize(BigNumber a)
    {
    	reduce(a);
    	if(a.value.length > (a.numberOfInitBits/CELL_SIZE) && a.spart<a.value.length && a.spart <= (a.numberOfInitBits/CELL_SIZE))
    	{
    		int[] newArray = new int[a.numberOfInitBits/CELL_SIZE];
    		for(int i = 0; i < newArray.length; i++)
    		{
    			newArray[i] = a.value[i];
    		}
    		a.value = newArray;
    	}
    }
    
    public String toString16(OutputFormat outType)
    {
    	String result = "";
    	String tmpResult = "";
        if(outType==OutputFormat.delimiter)
        {
        	return null;
        }
        if(outType==OutputFormat.allHex)
        {
        	BigNumber divisionResult = new BigNumber(this);
        	if(checkIfZero(this))
        	{
        		result = (BigNumber.repeatStringXTimes("0", (this.value.length*CELL_SIZE) / CELL_SIZE_HEX) + "0").substring("0".length());
        		return toStringSign() + "0x" + result;
        	}
            while(!checkIfZero(divisionResult))
            {
            	// we have to store currentDivisionResult in seperat variable since _divmod(c,c,..) bzw. divmod10(c,c) is not possible
            	BigNumber divisionResultCurrent = new BigNumber(0);
            	BigNumber r = divmod16(divisionResultCurrent, divisionResult, true);
            	tmpResult = tmpResult + Integer.toHexString(r.value[0]);
            	divisionResult = divisionResultCurrent;
        	}
            for(int i = (tmpResult.length()-1); i>-1; i--)
            {
            	result = result + tmpResult.charAt(i);
            }
            result = (BigNumber.repeatStringXTimes("0", (this.value.length*CELL_SIZE) / CELL_SIZE_HEX) + result).substring(result.length());
            result = toStringSign() + "0x" + result;
        	return result;
    	}
        else //if(outType==OutputFormat.hex)
        {
        	BigNumber divisionResult = new BigNumber(this);
        	if(checkIfZero(this))
        	{
        		result = "0";
        		return toStringSign() + (checkIfLengthIsEven(result) ? "0x" : "0x0") + result;
        	}
            while(!checkIfZero(divisionResult))
            {
            	// we have to store currentDivisionResult in seperat variable since _divmod(c,c,..) bzw. divmod10(c,c) is not possible
            	BigNumber divisionResultCurrent = new BigNumber(0);
            	BigNumber r = divmod16(divisionResultCurrent, divisionResult, true);
            	tmpResult = tmpResult + Integer.toHexString(r.value[0]);
            	divisionResult = divisionResultCurrent;
        	}
            for(int i = (tmpResult.length()-1); i>-1; i--)
            {
            	result = result + tmpResult.charAt(i);
            }								        	
            result = toStringSign() + (checkIfLengthIsEven(result) ? "0x" : "0x0") + result;
        	return result;
        }
    }
    
    public static boolean checkIfLengthIsEven(String result)
    {
    	return result.length() % 2 == 0;
    }
    
    
    
    private String toString16Value() {
        return null;
    }
    
    
    
    public String toString16()
    {
        return toString16(OutputFormat.hex);
    }


    
//	    start evaluation
//	    a deci: -8
//	    qResult: 1
//	    rResult: 7
//	    b deci: +17
//	    result deci: 0
//	    is: +0x0
//	    should be: -0x1
//	    r is: -0x8
//	    r should be: +0x9
								    
								    
// rResult: 9
//	qResult: 1
//	rResult: 9
//	qResult: 1
//	rResult: 9
//	qResult: 1
//	rResult: 9
    
    public String toString10()
    {
        String s = "";
        String t = "";
    	BigNumber divisionResult = new BigNumber(this);
    	if(checkIfZero(this))
    	{
    		return this.toStringSign() + "0";
    	}
        while(!checkIfZero(divisionResult))
        {        	
        	// we have to store currentDivisionResult in seperat variable since _divmod(c,c,..) bzw. divmod10(c,c) is not possible
        	BigNumber divisionResultCurrent = new BigNumber(0);
        	BigNumber r = divmod10(divisionResultCurrent, divisionResult, true);
        	s = s + r.value[0];
        	divisionResult = divisionResultCurrent;
        }
        for(int i = (s.length()-1); i>-1; i--)
        {
        	t = t + s.charAt(i);
        }
        return this.toStringSign() + t;
    }
    
    
    public void toBigInt(String str) {
        // format +0x000, -0x000, 0x000, +0000, -000, 000,

    	String workingString = str;
    	this.sign = sign.negative;
    	if(workingString.charAt(0) == '-')
    	{
    		this.sign = sign.negative;
    		workingString = workingString.substring(1);
    	}
    	else if(workingString.charAt(0) == '+')
    	{
    		this.sign = sign.positive;
    		workingString = workingString.substring(1);
    	}
    	else
    	{
    		this.sign = sign.positive;
    	}

    	if(workingString.length() > 2 && workingString.charAt(1) == 'x')
    	{
    		// hex-flow
        	workingString = workingString.substring(2);
    		this.toBigInt16(workingString);
    	}
    	else
    	{
    		// deci-flow
    		this.toBigInt10(workingString);
    	}

    }
    
    
    
    protected void shiftLeftCell(int cell) {
    }

    
    
    
    
    private void toBigInt16(String str)
    {

    	this.value[0] = Integer.parseInt("" + str.charAt(0),16);

    	for (int i = 1; i < str.length(); i=i+1)
    	{
    		mul16(this);
    		_addInt(this, (short) Integer.parseInt("" + str.charAt(i),16));
    	}
    	reduce(this);
    }
    
    public int stringtoInt(String tmp)
    {
    	return Integer.valueOf(tmp);
    }

    
    public int ord(char tmp)
    {
    	return tmp-'0';
    }
    
    private void toBigInt10(String str)
    {
    	this.value[0] = this.ord(str.charAt(0));
    	for (int i = 1; i < str.length(); i++)
    	{
    		mul10(this);
    		_addInt(this, (short) ord(str.charAt(i)));	// eig this = _addInt...
    	}
    	reduce(this);
    }
    
    
//    126 : 2 =  63  Rest: 0
//    	      63 : 2 =  31  Rest: 1
//    	      31 : 2 =  15  Rest: 1
//    	      15 : 2 =   7  Rest: 1
//    	       7 : 2 =   3  Rest: 1
//    	       3 : 2 =   1  Rest: 1
//    	       1 : 2 =   0  Rest: 1
//

    
    /**
     * multiplications
     */
    public static void _mul(BigNumber c, BigNumber a, BigNumber b, signt sign) {
        // c must be BigInt('0');
        // no processing of the signs, all values are interpreted to be positive
    	
    	sameSize(a, b);
    	c.sign = sign;
    	int tmp=0;
    	try {
			resize(c, (short)(2*a.spart+1), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	for(int i =0; i <=b.spart-1;i++)
    	{
    		for(int j=0; j <=a.spart-1; j++)
    		{
    			tmp = a.value[j] * b.value[i];
    			addCell2(c,i+j,tmp);
    		}
    	}
    	
    	reduce(a);
    	reduce(b);
        reduce(c);
        reduceArrayIfpossibleToInitBitSize(c);
    }
    
    
    
    private static void _mulCell(BigNumber c, BigNumber a, int b)
    {
        // c must be BigInt('0');
        // b is a cell
        // no processing of the signs, all values are interpreted to be positive
    	
    	c.setNewValue(new BigNumber(0));
    	try {
			resize(c, (short) (a.spart+1), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	int tmp;
    	
//    	System.out.println("begin");
//    	System.out.println("a: " + a.toString10());
//    	System.out.println("b: " + b);
//    	System.out.println("c: " + c.toString10());
    	
    	
    	for(int i = 0; i <= a.spart-1; i++) ///////////////////////////////////// glaube <= statt <
    	{
//    		System.out.println("in loop"); // 1 -> deshalb muss a.spart==1
    		tmp = a.value[i] * b;
//    		System.out.println("in loop tmp: " + tmp); // 10*6553 = 65530 -> ist richtig -> fehler muss in addCell2 liegen
    		addCell2(c, i, tmp);
    	}
    	reduce(c);
    	
//    	System.out.println("after");
//    	System.out.println("a: " + a.toString10());
//    	System.out.println("b: " + b);
//    	System.out.println("c: " + c.toString10());
    }
    
    
    
    
    public static void mul(BigNumber c, BigNumber b1, BigNumber b2) {
    	signt2 returnValue = lookAtSign(b1,b2);
    	switch(returnValue)
    	{
    		case pp:
    			_mul(c,b1,b2,signt.positive);
    			break;
    		case np:
    			_mul(c,b1,b2,signt.negative);
    			break;
    		case pn:
    			_mul(c,b1,b2,signt.negative);
    			break;
    		case nn:
    			_mul(c,b1,b2,signt.positive);
    			break;
    		default:
    			System.out.println("Error");
    	}
        
    }
    
    
//    public static void mulKara(BigNumber c, BigNumber a, BigNumber b) {
//    	signt2 returnValue = lookAtSign(a,b);
//    	switch(returnValue)
//    	{
//    		case pp:
//    			_mulKara(c,a,b,signt.positive);
//    			break;
//    		case np:
//    			_mulKara(c,a,b,signt.negative);
//    			break;
//    		case pn:
//    			_mulKara(c,a,b,signt.negative);
//    			break;
//    		case nn:
//    			_mulKara(c,a,b,signt.positive);
//    			break;
//    		default:
//    			System.out.println("Error");;
//    	}
//        
//    }

    
    
//    public static void _mulKara(BigNumber c, BigNumber a, BigNumber b, signt sign)
//    {
//    	c.sign = sign;
//    	sameSize(a,b);
//    	try {
//			resize(c,(short) (2*a.spart+1));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	if(a.spart<=LIMIT_KARATSUBA)
//    	{
//    		_mul(c,a,b,sign);
//    		return;
//    	}
//    	
//    	// eventuell muss hier ein ceil kommen
//    	// eventuell muss das in bits umgerechnet werden
//    	int partTmp = (int) Math.ceil(a.spart / 2);
//    	int part = partTmp * CELL_SIZE;
//    	
//    	Tuple<BigNumber, BigNumber> tuple = BigNumber.split(a, part);
//    	BigNumber D = tuple.x;
//    	BigNumber E = tuple.y;
//    	
//    	Tuple<BigNumber, BigNumber> tuple2 = BigNumber.split(b, part);
//    	BigNumber F = tuple.x;
//    	BigNumber G = tuple.y;
//    	
//    	BigNumber K = new BigNumber();
//    	add(K,D,E);
//    	
//    	BigNumber L = new BigNumber();
//    	add(L,F,G);
//    	
//    	BigNumber z0 = new BigNumber();
//    	_mulKara(z0, E,G, sign);
//    	
//    	BigNumber z1 = new BigNumber();
//    	_mulKara(z1, K,L, sign);
//    	
//    	BigNumber z2 = new BigNumber();
//    	_mulKara(z2, D,F, sign);
//    	
//    	
//    	
//    	
////    	 c:= z2*2**(2*part)  +   (z1-z2-z0)*2**part  +   z0;
//    	
//    	// first part
//    	BigNumber first = new BigNumber();
//    	BigNumber firstTmp1= BigNumber.powWithBaseTwo(2*part); //(int) Math.pow(2, (2*part));
//    	_mulKara(first, z2, firstTmp1, sign); // (1. part)
//    	
//    	//second part
//    	BigNumber second = new BigNumber();
//    	BigNumber secondSupport = new BigNumber();
//    	BigNumber secondTmp1 =  BigNumber.powWithBaseTwo(part); //(int) Math.pow(2, (part));
//    	BigNumber.sub(secondSupport, z1, z2);
//    	BigNumber.sub(secondSupport, secondSupport, z0);
//    	_mulKara(second, secondSupport, secondTmp1, sign); // (1. part)
//
//    	// third part
//    	z0 = z0;
//    	
//    	// result
//    	BigNumber.add(c, first, second);
//    	BigNumber.add(c, c, z0);
//    	
//	}
    
    // a = number to split
    // part =  k*cell size -> 16, 32, ...
    public static Tuple<BigNumber,BigNumber> split(BigNumber a, int part)
    {
    	
    	// Tuple(d,e)  // spart(D)<=spart(E) // der kleinere Teil ist links
    	return null;
    }
    
    
    
    public static void shiftLeftXTimes(BigNumber c, int count)
    {
    	int maxPerIteration = CELL_SIZE -1;
    	int howOften = count / maxPerIteration; // CELL_Size= 15 = max number you can insert in shiftLeft as parameter since Cell_Size is 16
    	int left = count % maxPerIteration;
    	for(int i =0; i < howOften; i++)
    	{
    		BigNumber.shiftLeft(c, maxPerIteration);
    	}
		BigNumber.shiftLeft(c, left);
    }
    
    public static BigNumber powWithBaseTwo(int exponent)
    {
	//    	BigNumber.powWithBaseTwo(0) = 1
	//    	BigNumber.powWithBaseTwo(1) = 2
	//    	BigNumber.powWithBaseTwo(2) = 4
    	BigNumber result = new BigNumber("1");
    	BigNumber.shiftLeftXTimes(result, exponent);
    	return result;
    }
    
    
    
    
    // 2. Parameter gibt an, ab welcher Stelle die Zelle b im Array von a addiert werden soll
    public static void addCell2(BigNumber a, int index, int b)
    {
    	try {
			resize(a, (short) (a.spart+1), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	int tmp = 0;
    	int over = b;
    	while(over!=0)
    	{
//    		 tmp:= a[index]+over mod base;
    		tmp = a.value[index]+ (over & CELL_MASK);
    		// a[index++]:= tmp mod base;
    		a.value[index] = tmp & CELL_MASK;
//    		over:= tmp div base + over div base; 
    		over = ((tmp>>CELL_SIZE)&CELL_MASK) + ((over>>CELL_SIZE)&CELL_MASK);
    		index=index+1;
    	}
    	
    	reduce(a);
    }
    
    
    
    
    
    
    
    /**
     * multiplication by 10 function: a:= b*10;
     * No allocation of a, b
     * @param a a= mul10(b);
     * @param b
     */
    public static void mul10(BigNumber a, BigNumber b) {
        a= mul10(b);
    }
    
    
    
    
    public static BigNumber mul10(BigNumber a) {
    	BigNumber workingObject = new BigNumber(a);    	
    	
    	shiftLeft(a,2); // c:= c*4
    	add(a,a,workingObject); // c:= c+a
    	shiftLeft(a,1); // c:= c*2
    	
    	 return a;	
    }
    
    public static BigNumber mul16(BigNumber a) {
    	shiftLeft(a,4); // c:= c*4
    	return a;
    }
    
    public static void shiftLeft(BigNumber a, int count) {
    	
//        System.out.println("DUAL begin: " + printBigNumbersDual(a));

    	if(!(count<CELL_SIZE))
    	{
    		System.out.println("Error: count < CELL_SIZE");
    		return;
    	}
    	
        short over = 0;
        

//        try {
//        	if(a.value[a.spart] != 0)
//        	{
//    			resize(a,(short) (a.spart+1));
//        	}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
        
		try
		{
			resize(a,(short) (a.spart+1), false);
		}
		catch (Exception e)
		{
			
		}
		

        // also für jede Zelle des signifikanten teils
        for(int i =0; i <= (a.spart-1); i++)
        {
        	// deshalb benutzen wir nur die hälfte des ints (dieses System ist zwar 64bit, wir betrachten es aber als 32bit und deshalb benutzen wir 16 bit für cellsize)
        	
        	int tmp =(a.value[i]<<count)+over;

//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
//        	a.value[i] = tmp % CELL_SIZE;
        	a.value[i]= (int)(tmp & CELL_MASK); // tmp % 65536;
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	over =  (short)((tmp>>CELL_SIZE) & CELL_MASK);
        }


        
    	try
    	{
    		a.value[a.spart] = over;
    	}
    	catch(ArrayIndexOutOfBoundsException e)
    	{
//    		e.printStackTrace();
    	}
    	
        if(over!=0)
        {
        	a.spart += 1;
        }
//        System.out.println("DUAL after: " + printBigNumbersDual(a));
//        reduceArrayIfpossibleToInitBitSize(a);
        reduce(a);
    }
    
    
    
    
    public static void shiftRight(BigNumber a, int count)
    {
		if(!(count<CELL_SIZE))
		{
			System.out.println("Error: count < CELL_SIZE");
			return;
		}
		
		int under = 0; //(short) a.value[a.spart-1];
		try
		{
			resize(a,(short) (a.spart+1), false);
		}
		catch (Exception e)
		{
			
		}
		// 65536 possibilities -> 65535 =max value
		for(int i =a.spart-1; i >= 0; i--)
		{
			int tmp =(a.value[i]>>count)+under;
			int formerValue = a.value[i];
        	a.value[i]= (int)(tmp & CELL_MASK);
        	under = ((formerValue<<(CELL_SIZE-1)) & CELL_MASK);
    	}
		try
		{
//			a.value[a.spart] = under;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			
		}
		reduce(a);
    }
    
    // Es wird der signifikante Teil auf die Länge newSize Zellen gebracht.
    public static void resize(BigNumber a, short newSize, boolean shouldProbPhysicalExtend) throws Exception
    {
        // newSize = number of cells, not bits
    	
    	if(!(newSize>0))
    	{
    		throw new Exception("sewSite > 0");
    	}
    	
    	
    	
    	if(a.spart > newSize)
    	{
    		reduce(a);
    		if(a.spart>newSize)
    		{
    			throw new Exception("cant resize...");
    		}
    	}
    	
    	if(a.spart<newSize)
    	{
    		expand(a,newSize, shouldProbPhysicalExtend);
    	}    	
    }
    
    
    
	//Es kann sein, dass expand() das Array auch physikalisch verlängern
	//muss. Dann kann die Speichergröße verschiedener BigInt-Objekte
	//unterschiedlich sein.
	
	// denke, dass damit die length des array gemeint ist
    public static void expand(BigNumber a, short newSize, boolean shouldProbPhysicalExtend) throws Exception
    {
    	if(newSize >= a.value.length && shouldProbPhysicalExtend == false)
    	{
    		throw new ArrayIndexOutOfBoundsException("cant resize");
    	}
    	a.spart = newSize;
    	 if(a.value.length<=newSize && shouldProbPhysicalExtend)
    	 {
    		 int[] newArrayValues = new int[(newSize+1)];
    		 for(int i = 0; i < a.value.length; i++)
    		 {
    			 newArrayValues[i] = a.value[i];
    		 }
    		 a.value = newArrayValues;
    	 }
    }
    
    
    

    public static void _add(BigNumber c, BigNumber a, BigNumber b, signt s) {
        // no processing of the signs, all values are interpreted to be positive
        
    	int tmp, over =0;
    	sameSize(a,b);
    	try
    	{
        	if(c.value[c.spart] != 0)
        	{
//        		System.out.println("is: " + a.spart);
        		resize(c,(short) (a.spart+1), true);
//        		System.out.println("is now: " + c.spart);
        	}
		} catch (ArrayIndexOutOfBoundsException e)
    	{
//			throw new SizeOverflowException("Size-Overflow");
		}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	for(int i =0; i<=a.spart-1; i++)
    	{
    		
    		
    		tmp = a.value[i] + b.value[i] + over;
//    		c.value[i] = tmp % CELL_SIZE;
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	c.value[i]= (int)(tmp & CELL_MASK);

//    		over = tmp / CELL_SIZE; // 0 or 1
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	over =  (short)((tmp>>CELL_SIZE) & CELL_MASK);
    	}
    	
    	if(over!=0)
    	{
	    	try
	    	{
	    		c.value[a.spart] = over;
	    	}
	    	catch(Exception e)
	    	{
	    		throw new SizeOverflowException("Size overflow");
	    	}
    	}
    		
    	c.sign = s;
    	reduceArrayIfpossibleToInitBitSize(c);
    	reduce(a);
    	reduce(b);
		reduce(c);

    }
    
    
    public static void _addInMul(BigNumber c, BigNumber a, BigNumber b, signt s) {        
    	int tmp, over =0;
    	sameSize(a,b);
    	try
    	{
        	if(c.value[c.spart] != 0)
        	{
        		resize(c,(short) (a.spart+1), true);
    		}
		} catch (ArrayIndexOutOfBoundsException e)
    	{
			
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	for(int i =0; i<=a.spart-1; i++)
    	{
    		
    		
    		tmp = a.value[i] + b.value[i] + over;
//    		c.value[i] = tmp % CELL_SIZE;
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	c.value[i]= (int)(tmp & CELL_MASK);

//    		over = tmp / CELL_SIZE; // 0 or 1
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	over =  (short)((tmp>>CELL_SIZE) & CELL_MASK);
    	}
    	
    	if(over!=0)
    	{
    		c.value[a.spart] = over;
    	}
    	c.sign = s;
    	reduceArrayIfpossibleToInitBitSize(c);
    	reduce(a);
    	reduce(b);
		reduce(c);

    }
    

    
    
    
    private static void sameSize(BigNumber a, BigNumber b)
    {
    	reduce(a);
    	reduce(b);
		try
		{
	    	if(a.spart>b.spart)
	    	{
				resize(b, a.spart, true);
	    		return;
	    	}
	    	if(b.spart>a.spart)
	    	{
	    		resize(a,b.spart, true);
	    		return;
    	}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    
    
    
    // "normale" Schulbuchaddition ohne Optimierung
    
    
    
    // Addition mit Vorzeichenbehandlung
    public static void add(BigNumber c, BigNumber a, BigNumber b) {
    	signt2 returnValue = lookAtSign(a,b);
    	switch(returnValue)
    	{
    		case pp:
    			_add(c,a,b, signt.positive);
    			break;
    		case np:
    			if(_le(a, b))
    				_sub(c,b,a,signt.positive);
    			else
    				_sub(c,a,b,signt.negative);
    			break;
    		case pn:
    			if(_le(a, b))
    				_sub(c,b,a,signt.negative);
    			else
    				_sub(c,a,b,signt.positive);
    			break;
    		case nn:
    			_add(c,a,b,signt.negative);
    			break;
			default:
				System.out.println("Error2");
    	}
    	if(checkIfZero(c))
    		c.correctNegativeZero();
    }

    
    /**
     * Subtraction c:= a-b
     * No allocation of a, b, c
     */
    private static void _sub(BigNumber c, BigNumber a, BigNumber b, signt s) {
        // no processing of the signs, all values are interpreted to be positive
        // c==a or c==b is possible
    	
    	int tmp, over = 0;
    	sameSize(a,b);
    	try {
        	if(c.value[c.spart] != 0)
        	{
        		resize(c, (short) (a.spart+1), true);
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for(int i =0; i <= a.spart-1; i++)
		{
    		tmp = a.value[i] - b.value[i] + over;
//    		c.value[i] = tmp % CELL_SIZE;
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	c.value[i]= (int)(tmp & CELL_MASK);

//    		over = tmp / CELL_SIZE; // 0 or 1
//        	over = tmp / CELL_SIZE; // sein skript:  tmp / base // ich denke, dass er hier (mit div) den Zellen-index getten möchte und daher sollte base glaube ich 2^16 (ca 65000) sein also 1111111111111111 und nicht einfach CellSize
        	over =  (short)((tmp>>CELL_SIZE) & CELL_MASK);
		}
		c.value[a.spart] = over;
		c.sign = s;
		reduce(a);
		reduce(b);
		reduce(c);
    	if(checkIfZero(c))
    		c.correctNegativeZero();
    }
    
    
    
    
    public static void sub(BigNumber c, BigNumber a, BigNumber b) {
    	signt2 returnValue = lookAtSign(a,b);
    	switch(returnValue)
    	{
    		case pp:
    			if(_le(a, b))
    			{ ///////////////////////////////
    				_sub(c,b,a, signt.negative);
    			}
    			else
    				_sub(c,a,b, signt.positive);
    			break;
    		case np: //////////////
    			_add(c,a,b,signt.negative);
    			break;
    		case pn:
    			_add(c,a,b,signt.positive);
    			break;
    		case nn:
    			if(_le(a, b))
    			{
    				_sub(c,b,a,signt.positive);
    			}
    			else
    			{
    				_sub(c,a,b,signt.negative);
    			}
    			break;
			default:
				System.out.println("Error2");
    	}
    }
    
    
    
    private static signt2 lookAtSign(BigNumber a, BigNumber b) {
        if(a.sign==signt.positive && b.sign==signt.positive) {
            return signt2.pp;
        }
        if(a.sign==signt.negative && b.sign==signt.positive) {
            return signt2.np;
        }
        if(a.sign==signt.positive && b.sign==signt.negative) {
            return signt2.pn;
        }
        return signt2.nn;
    }
    
    
    // add int in place (with siteeffects -> effects this.object directly)
    // c and b have to be positive
    public static void _addInt(BigNumber c, short b)
    {
    	try
    	{
			resize(c, (short) (c.spart+1), true);
		}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	int tmp = 0;
    	int over = b;
    	int index = 0;
    	while(over!=0)
    	{
    		tmp = c.value[index]+ (over & CELL_MASK);
    		c.value[index] = tmp & CELL_MASK;
    		over = ((tmp>>CELL_SIZE)&CELL_MASK) + ((over>>CELL_SIZE)&CELL_MASK);
    		index=index+1;
    	}
    	reduceArrayIfpossibleToInitBitSize(c);
    	reduce(c);
    }
    
    // return true if a lessequals b
    // return false if a not lessquals b
    public static boolean _le(BigNumber a, BigNumber b) {
        // compare absolute values, ignore sign
        // do not change spart
        boolean check = false;
        if(a.spart < b.spart)
        {
        	return true;
        }
        else if(a.spart == b.spart)
        {
        	for(int i=a.spart-1; i>-1; i--)
        	{
        		if(a.value[i] < b.value[i])
        		{
        			return true;
        		}
        		if(a.value[i] > b.value[i])
        		{
        			return false;
        		}
        		if(i == 0 && a.value[i] == b.value[i])
        		{
        			return true;
        		}
        	}
        }
        else
        {
        	return false;
        }
        System.out.println("hier darf methode niemals landen");
        return false;
    }
    
    public static boolean _leSign(BigNumber a, BigNumber b)
    {
    	if(a.sign==signt.positive && b.sign==signt.positive)
    	{
    		return _le(a,b);
    	}
    	else if(a.sign==signt.negative && b.sign==signt.positive)
    	{
    		return true;

    	}
    	else if(a.sign==signt.positive && b.sign==signt.negative)
    	{
    		return false;

    	}
    	else // both negativ
    	{
    		return _le(b,a);
    	}
    }
    
    // according to the example project these compare function have to compare absolute values, ignore sign
    //
    // return true if a less than b
    // return false if a not less than b
    public static boolean _lt(BigNumber a, BigNumber b) {
        // compare absolute values, ignore sign
        // do not change spart
        boolean check = false;
        if(a.spart < b.spart)
        {
        	return true;
        }
        else if(a.spart == b.spart)
        {
        	for(int i=a.spart-1; i>-1; i--)
        	{
        		if(a.value[i] < b.value[i])
        		{
        			return true;
        		}
        		if(a.value[i] > b.value[i])
        		{
        			return false;
        		}
        		if(i == 0 && a.value[i] == b.value[i])
        		{
        			return false;
        		}
        	}
        }
        else
        {
        	return false;
        }
        System.out.println("hier darf methode niemals landen");
        return false;
    }
    
    public static boolean _ltSign(BigNumber a, BigNumber b) {
    	if(a.sign==signt.positive && b.sign==signt.positive)
    	{
    		return _lt(a,b);
    	}
    	else if(a.sign==signt.negative && b.sign==signt.positive)
    	{
    		return true;

    	}
    	else if(a.sign==signt.positive && b.sign==signt.negative)
    	{
    		return false;

    	}
    	else // both negativ
    	{
    		return _lt(b,a);
    	}
    }
    
    
    // return true if a equals b
    // return false if a not equals b
    public static boolean _equals(BigNumber a, BigNumber b) {
        // compare absolute values, ignore sign
        // do not change spart
        boolean check = false;
        if(a.spart < b.spart)
        {
        	return false;
        }
        else if(a.spart == b.spart)
        {
        	for(int i=a.spart-1; i>-1; i--)
        	{
        		if(a.value[i] < b.value[i])
        		{
        			return false;
        		}
        		if(a.value[i] > b.value[i])
        		{
        			return false;
        		}
        		if(i == 0 && a.value[i] == b.value[i])
        		{
        			return true;
        		}
        	}
        }
        else
        {
        	return false;
        }
        System.out.println("hier darf methode niemals landen");
        return false;
    }
    
    
    public static boolean checkIfZero(BigNumber a)
    {
    	if(a.spart == 1 && a.value[a.spart-1] == 0)
    	{
    		return true;
    	}
    	else
    		return false;
    }
    
    
    // asumption: base==CELL_SIZE
    public static int estimateAlter(int cUpper, int cLower, int divisor)
    {
    	int dividend = cUpper;
    	if(dividend < divisor)
    	{
    		dividend = (cUpper<<CELL_SIZE) + cLower; // shift one cell to the left
    	}
    	int estimate = dividend / (int) divisor;
    	return estimate;
    }
    
    // asumption: base==CELL_SIZE
    public static int estimateAlterWithoutSecondCell(int cUpper, int divisor)
    {
    	int dividend = cUpper;
    	int estimate = dividend / divisor;
    	return estimate;
    }
    
    
    public static short estimateKnuth(short cUpper, short cLower, short divisor)
    {
    	int divident = cUpper * CELL_SIZE + cLower;
    	int estimate = divident / divisor;
    	if((estimate / CELL_SIZE) != 0)
    	{
    		estimate = CELL_MASK;
    	}
    	return (short) estimate;
    	
    }
    
    
    // calculation of 2 values
    // q = Divisionsergebnis (somehow also a return-value since it is given per reference)
    // r = Rest (== return-value)
    public static BigNumber _divmod(BigNumber q, BigNumber a, BigNumber b, signt s, boolean UsedFortoString)
    {

    	reduce(a);
    	reduce(b);

    	q.setNewValue(new BigNumber((short) q.numberOfInitBits,0));
    	BigNumber r = new BigNumber(a, signt.positive);

    	if(checkIfZero(b))
    	{
    		throw new IllegalArgumentException("Division by zero");
    	}
    	if(a.spart<b.spart)
    	{
    		if(a.sign == signt.negative || b.sign == signt.negative)
    		{
	    		if(a.sign==signt.negative && !_equals(b, new BigNumber(0)))
	    		{
	    			// qResult = qResult + 1;
	    			BigNumber.add(q, q, new BigNumber("1"));
	    			
	    			// rResult = (bLong - (Math.abs(aLong) % bLong)) % bLong;
	    			BigNumber aTemp = new BigNumber(a);
	    			aTemp.sign = signt.positive;
	    			BigNumber bTemp = new BigNumber(b);
	    			bTemp.sign = signt.positive;
	    			BigNumber notUsed = new BigNumber("0");
	    			BigNumber c = new BigNumber("0");
	    			BigNumber.sub(c, bTemp, BigNumber.div(notUsed, aTemp, bTemp));
	    			r = BigNumber.div(notUsed, c, bTemp);
	        		q.sign = s;
	    			return r;
	    		}
	    		
	    		if(b.sign==signt.negative && !_equals(b, new BigNumber(0)))
	    		{
	    			// qResult = qResult + 1;
	    			BigNumber.add(q, q, new BigNumber("0"));
	    			r=a;
	        		q.sign = signt.positive;
	    			return r;
	    		}
    		}
    		
    		r = a;
    		return r;
    	}
    	if(a.spart==b.spart && _lt(a, b) && a.sign==signt.positive)
    	{
    		r = a;
    		return r;
    	}
    	if(a.spart<3 && b.spart<3)
    	{

    		String aString1 = Integer.toString(a.value[1],2);
    		String aString2 = Integer.toString(a.value[0],2);
    		// make sure the bitString has Cell_Size (16) bits
            String aString1Adjusted = (BigNumber.repeatStringXTimes("0", CELL_SIZE) + aString1).substring(aString1.length());
            String aString2Adjusted = (BigNumber.repeatStringXTimes("0", CELL_SIZE) + aString2).substring(aString2.length());
            String aString = aString1Adjusted + aString2Adjusted;
            long aLong = Long.parseLong(aString,2);
            
            
            
    		String bString1 = Integer.toString(b.value[1],2);
    		String bString2 = Integer.toString(b.value[0],2);
            String bString1Adjusted = (BigNumber.repeatStringXTimes("0", CELL_SIZE) + bString1).substring(bString1.length());
            String bString2Adjusted = (BigNumber.repeatStringXTimes("0", CELL_SIZE) + bString2).substring(bString2.length());
            String bString = bString1Adjusted + bString2Adjusted;
            long bLong = Long.parseLong(bString,2);
            

            long qResult = (long)(aLong/bLong);
            long rResult = aLong % bLong;
    		

    		if(rResult != 0 && (a.sign==signt.negative || b.sign==signt.negative) && UsedFortoString==false && !_equals(b, new BigNumber(0)))
    		{
	    		if(a.sign == signt.negative)
	    		{
	    			qResult = qResult + 1;  // (long) Math.floor(qResult); // qResult + 1;
	    			rResult = (bLong - (Math.abs(aLong) % bLong)) % bLong;
	        		if(qResult == 0)
	        		{
	        			s = signt.positive;
	        		}
	    		}
	    		else if(a.sign==signt.positive && b.sign==signt.positive)
	    		{
	    			
	    		}
	    		else // (b.sign == signt.negative)
	    		{
	    			qResult = qResult + 0; //(long) Math.ceil(qResult); // qResult + 0;
	    			rResult = aLong % bLong;
	        		if(qResult == 0)
	        		{
	        			s = signt.positive;
	        		}
	    		}
    		}
    		
    		r = new BigNumber(Long.toString(rResult));
    		q.setNewValue(new BigNumber((short) q.numberOfInitBits, Long.toString(qResult)));
    		q.sign = s;


    		return r;
    	}
    	


    	

    	
    	
    	// ... Division ... according to p.8
    	int K = b.spart; // e.g.: 1
    	int L = a.spart-K;
    	
    	// reset r according to school-divison method
    	// set r according to school-division method to:
    	//	- r.spart has to match b.spart
    	//	- so imagin each cell==1 number -> then get as many number from the input number a (start left to right) to the rest r as there are number in b
    	//	- translated to the given implementation: get as many cells (start left to right) from a to r as there are cells in b
    	// 	-> e.g. a.spart=3, b.spart=1 -> get one cell (first cell of a == a.value[2]) to r, after that you can check if r is dividable by b
    	//	- pay attention here we working with cells -> since that it can happen that we get r.spart and b.spart same but get 1/10 since in first cell of a -> a 1 is stored
    	//	short: thats a small loop to get the first elements and nothing more
    	r.setNewValue(new BigNumber("0"));
    	for(int i = 0; i <= (K-1); i++)
    	{
    		r.value[i] = a.value[L+i];
		}
    	reduce(r);

    	
    	

    	
    	
    	// main loop
    	// iterates through L -> so according to the school-book-division each cell/number from left to right will be devided by b
    	// but since the have the loop above we dont start most outer left (since this values are already stored in r now)
    	// -> we going from left to right K-1 cells (see loop above) and then continue from left to right
    	for(int i = L; i >= 0; i--)
    	{

    		reduce(a);
    		reduce(b);
    		reduce(q);

    		
    		// changed from short to int -> since e can be max (from the algorithm and the cell values) 2^16 but, short can only display
    		// 2^15 and after around 32k e goes into the minus section e.g. -30k
    		int e;
    		if(i==0)
    		{
    			try
    			{
    				e = estimateAlter(r.value[r.spart-1], r.value[r.spart-2], b.value[b.spart-1]);
				}
    			catch(ArrayIndexOutOfBoundsException exception)
    			{
            		e = estimateAlterWithoutSecondCell(r.value[r.spart-1], b.value[b.spart-1]);
    			}

    		}
    		else if(r.spart>1)
    		{
        		e = estimateAlter(r.value[r.spart-1], r.value[r.spart-2], b.value[b.spart-1]);
    		}
    		else
    		{
        		e = estimateAlterWithoutSecondCell( r.value[r.spart-1], b.value[b.spart-1]);

    		}

    		
    		
//    		0000000000000001	0000000000000000	0000000000000000	
//    		tmp before: 0
//    		b: 10
//    		e: 0
//    		tmp after: 0
//    		bbb
//    		tmp before: 0
//    		b: 10
//    		e: 6553
//    		tmp after: 4294574080
//    		aaa
//    		hi
    		
    		// should be: tmp:= b*e
    		
    		BigNumber tmp = new BigNumber(0);
//    		System.out.println("tmp before: " + tmp.toString10());
//    		System.out.println("b: " + b.toString10());
//    		System.out.println("e: " + e);
    		_mulCell(tmp, b, e);
//    		System.out.println("tmp after: " + tmp.toString10());
    		
    	
    		
    		
    		
    		if(_lt(r, tmp))
    		{

    			e = downsizeEBySteps(tmp,b,e,r);

    		}
    		else
    		{

    			if(!_equals(tmp, r))
    			{
    				e = enlargeEBySteps(tmp,b,e,r);
    			}
    		}
    		

    		q.value[i] = e;
    		sub(r, r, tmp);
    		
    		
    		// after integration of estimation-function
    		// p.8
    		if(i!=0)
    		{
    			BigNumber.shiftLeftXTimes(r, 16);
    			BigNumber tmp11 = new BigNumber(0);
    			BigNumber.add(tmp11, r, new BigNumber(a.value[i-1]));
    			r.setNewValue(tmp11);
    			reduce(r);
    		}
    	}
    	
    	q.sign = s;
    	reduce(q);
    	reduce(r);
    	return r;
    }
    
    public static int downsizeEBySteps(BigNumber tmp, BigNumber b, int e, BigNumber r)
    {
    	reduce(b);
    	while(_le(r, tmp))
    	{
    		e = e-1;
    		sub(tmp, tmp, b);
		}
    	return e;
    }
    
    public static boolean enlargeWhileCondition(BigNumber r, BigNumber tmp, BigNumber b)
    {
    	BigNumber result = new BigNumber(0);
    	sub(result, r, tmp);
    	return _le(b, result);
    	
    }
    
    public static int enlargeEBySteps(BigNumber tmp, BigNumber b, int e, BigNumber r)
    {
    	
    	while(enlargeWhileCondition(r, tmp, b))
    	{
    		e = e+1;
    		add(tmp, tmp, b);
    	}
    	return e;
    }
    
    public static BigNumber divmod(BigNumber c, BigNumber a, BigNumber b, boolean usedForToString)
    {
    	signt2 returnValue = lookAtSign(a,b);
    	switch(returnValue)
    	{
    		case pp:
    			return _divmod(c,a,b,signt.positive,usedForToString);
    		case np:
    			return _divmod(c,a,b,signt.negative,usedForToString);
    		case pn:
    			return _divmod(c,a,b,signt.negative,usedForToString);
    		case nn:
    			return _divmod(c,a,b,signt.positive,usedForToString);
    		default:
    			System.out.println("Error");
    			return null;
    	}
    }
    
    public static BigNumber div10(BigNumber div, BigNumber a)
    {
    	return BigNumber.divmod10(div, a, false);
    }
    
    public static BigNumber div(BigNumber div, BigNumber a, BigNumber b)
    {
        // div:= a // b
        // return mod= a%b
        return BigNumber.divmod(div, a, b, false);
    }
    
    // _divmod(c,c,..) bzw. divmod10(c,c) gehen aber nicht.
    public static BigNumber divmod10(BigNumber c, BigNumber a, boolean usedForToString)
    {
    	BigNumber ten = new BigNumber((short) a.numberOfInitBits, 10);
    	return _divmod(c, a, ten, a.sign, usedForToString);
    }
    
    public static BigNumber divmod16(BigNumber c, BigNumber a, boolean usedForToString)
    {
    	BigNumber notTen = new BigNumber(16);
    	return _divmod(c, a, notTen, a.sign, usedForToString);
    }
    
    
    
    public static String printBigNumberInCellValues(BigNumber a)
    {
    	String tmp = "";
    	for(int i = a.spart-1; i >=0; i--)
    	{
    		tmp = tmp + a.value[i] + "\t";
    	}
    	return tmp;
    }
    
    public static String printBigNumbersDual(BigNumber a)
    {
    	String tmp = "";
    	for(int i = a.spart-1; i >=0; i--)
    	{
    		String number = Integer.toString(a.value[i],2);
    		tmp = tmp + (BigNumber.repeatStringXTimes("0", CELL_SIZE) + number).substring(number.length())  + "\t";
    	}
    	return tmp;
    }
    


}
