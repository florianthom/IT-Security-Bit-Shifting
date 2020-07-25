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

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
class BigNumber extends BigIntConfiguration implements Cloneable {
	
	// lenth of this.value
	// number of cells
    protected short size;    
    
	// "Es wird der signifikante Teil auf die Länge newSize Zellen gebracht."
	// length of significant part in cells // glaube in zellen, nicht in bits gerechnet
    protected short spart;
    
    // sign
    protected signt sign;
    
    // array itself
    // cell array
    protected int value[];
    

    
    
    
    
    protected BigNumber() {
        this((short)0);
    }

    protected BigNumber(String str) {
        this((short)0);
        toBigInt(str);
    }
    
    private BigNumber(short val) {
        this(ARRAY_BIT_SIZE_STATIC,val);
    }
    
    protected BigNumber(short bitsize, String str) {
        this(bitsize,(short)0);
        toBigInt(str);
    }

    

    protected BigNumber(short numberBitsInTotalForWholeArray, short val)
    {
    	this.size = (short) (numberBitsInTotalForWholeArray / CELL_SIZE);
    	this.value = new int[this.size];
    	this.sign = signt.positive;
    	this.value[0] = val;
		this.spart=1;
    }
    

    protected BigNumber(BigNumber tmp)
    {
    	this.size = tmp.size;
    	this.spart = tmp.spart;
    	this.sign = tmp.sign;
    	this.value = tmp.value.clone();
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void reduce(BigNumber a)
    {
        // the higher part above spart
    	
//        System.out.println(a.value[0]);
//        System.out.println(a.value[1]);
////        System.out.println(c.sign);
//        System.out.println(a.spart);
    	for(int i=a.value.length-1; i>-1; i--)
    	{
    		if(a.value[i]!=0)
    		{
    			// i ist der Index der höchsten Zelle
        		a.spart=(short) (i+1);
        		return;
    		}

    	}
    	
    }
       
    
 
    public void assign(BigInt a) {
        
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

    
    
    @Override
    public String toString() {
        return toString16();
    }

    
    
    private String toStringSign() {
        if (sign == signt.negative) {
            return "-";
        }
        return "+";
    }

    
    
    public String toString16(OutputFormat outType) {
        if(outType==OutputFormat.delimiter)
        {
        	return null;
        }
        if(outType==OutputFormat.allHex)
        {
        	return null;

        }
        else //if(outType==OutputFormat.hex)
        {
        	
        	return null;
        }
    }
    
    
    
    private String toString16Value() {
        return null;
    }
    
    
    
    public String toString16() {
        return toString16(OutputFormat.hex);
    }

    
    
    public String toString10() {
        return null;
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
    		workingString = workingString.substring(1);
    	}
    	else
    	{
    		this.sign = sign.positive;
    	}

    	if(workingString.length() > 2 && workingString.charAt(1) == 'x')
    	{
        	// hex-flow
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

    
    
    
    
    private void toBigInt16(String str) {
    	String workingString = str.substring(2);
    	int arrayIndex = 0;
//    	for ()
//    	{
//	        String hexstring = "FFFF";
//	        tmp1 = Integer.parseInt(hexstring, 16);
//			// und das mit jeder einzelnen Zelle, bis kein hexstring mehr vorhanden ist -> aufpassen dass man nicht machen kann value[i] = Interger.parseInt(str[i]), da dann die Zahl falsch herum eingelesen wird
//    	}
    	System.out.println(this.value);
    }

    
    public int ord(char tmp)
    {
    	return tmp-'0';
    }
    
    private void toBigInt10(String str) {
    	    	
    	this.value[0] = this.ord(str.charAt(0));
    	
    	 // testing
//		this.value[0] = 2;
//		_addInt(this, (short) 1000);
//		BigNumber tmp1 = mul10(this);
//		System.out.println(this.value[0]);
//		System.out.println(this.value[1]);
		
    	for (int i = 1; i < str.length(); i++)
    	{
    		mul10(this);
    		_addInt(this, (short) ord(str.charAt(i)));	// eig this = _addInt...
    	}
    	
//    	System.out.println(this.value[0]);
//    	for (int i : this.value) {
//    		System.out.println(i);
//			
//		}
    	
    	
    	
    	
    	
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
			resize(c, (short)(2*a.spart+1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
    	
        reduce(c);
    }
    
    
    
    private static void _mulCell(BigNumber c, BigNumber a, int b) {
        // c must be BigInt('0');
        // b is a cell
        // no processing of the signs, all values are interpreted to be positive
        
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
    
    
    public static void mulKara(BigNumber c, BigNumber a, BigNumber b) {
    	signt2 returnValue = lookAtSign(a,b);
    	switch(returnValue)
    	{
    		case pp:
    			_mulKara(c,a,b,signt.positive);
    			break;
    		case np:
    			_mulKara(c,a,b,signt.negative);
    			break;
    		case pn:
    			_mulKara(c,a,b,signt.negative);
    			break;
    		case nn:
    			_mulKara(c,a,b,signt.positive);
    			break;
    		default:
    			System.out.println("Error");;
    	}
        
    }
    
    public static void _mulKara(BigNumber c, BigNumber a, BigNumber b, signt sign)
    {
    	c.sign = sign;
    	sameSize(a,b);
    	try {
			resize(c,(short) (2*a.spart+1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(a.spart<=LIMIT_KARATSUBA)
    	{
    		_mul(c,a,b,sign);
    		return;
    	}
    	
    	// eventuell muss hier ein ceil kommen
    	// eventuell muss das in bits umgerechnet werden
    	int partTmp = (int) Math.ceil(a.spart / 2);
    	int part = partTmp * CELL_SIZE;
    	
    	Tuple<BigNumber, BigNumber> tuple = BigNumber.split(a, part);
    	BigNumber D = tuple.x;
    	BigNumber E = tuple.y;
    	
    	Tuple<BigNumber, BigNumber> tuple2 = BigNumber.split(b, part);
    	BigNumber F = tuple.x;
    	BigNumber G = tuple.y;
    	
    	BigNumber K = new BigNumber();
    	add(K,D,E);
    	
    	BigNumber L = new BigNumber();
    	add(L,F,G);
    	
    	BigNumber z0 = new BigNumber();
    	_mulKara(z0, E,G, sign);
    	
    	BigNumber z1 = new BigNumber();
    	_mulKara(z1, K,L, sign);
    	
    	BigNumber z2 = new BigNumber();
    	_mulKara(z2, D,F, sign);
    	
    	
    	
    	
//    	 c:= z2*2**(2*part)  +   (z1-z2-z0)*2**part  +   z0;
    	
    	// first part
    	BigNumber first = new BigNumber();
    	BigNumber firstTmp1= BigNumber.powWithBaseTwo(2*part); //(int) Math.pow(2, (2*part));
    	_mulKara(first, z2, firstTmp1, sign); // (1. part)
    	
    	//second part
    	BigNumber second = new BigNumber();
    	BigNumber secondSupport = new BigNumber();
    	BigNumber secondTmp1 =  BigNumber.powWithBaseTwo(part); //(int) Math.pow(2, (part));
    	BigNumber.sub(secondSupport, z1, z2);
    	BigNumber.sub(secondSupport, secondSupport, z0);
    	_mulKara(second, secondSupport, secondTmp1, sign); // (1. part)

    	// third part
    	z0 = z0;
    	
    	// result
    	BigNumber.add(c, first, second);
    	BigNumber.add(c, c, z0);
    	
	}
    
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
			resize(a, (short) (a.spart+1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int tmp = 0;
    	int over = b;
    	while(over!=0)
    	{
//    		 tmp:= a[index]+over mod base;
    		tmp = (a.value[index]+over) & CELL_MASK;
//    		a[index++]:= tmp mod base;
    		a.value[index++] = tmp & CELL_MASK;
//    		over:= tmp div base + over div base; 
    		over = ((tmp>>CELL_SIZE)&CELL_MASK) + ((over>>CELL_SIZE)&CELL_MASK);
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
    	

    	 return workingObject;	
    }
    
    
    public static void shiftLeft(BigNumber a, int count) {
    	
    	
    	
    	if(!(count<CELL_SIZE))
    	{
    		System.out.println("Error");
    	}
    	
        short over = 0;
        

        try {
        	if(a.value[a.spart] != 0)
        	{
        		System.out.println("hi1");
    			resize(a,(short) (a.spart+1));
        	}
		} catch (Exception e) {
			e.printStackTrace();
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

    	
        a.value[a.spart] = over;

        
        
        if(!(a.spart < a.size))
        {
        	System.out.println("Die Erhöhung des signifikanten Teils in der vorletzten Zeile setzt eine Prüfung auf die physikalische Länge voraus.");
        	System.out.println("Error");
        }
        
        if(over!=0)
        {
        	a.spart += 1;
        }

        
    }
    
    // Es wird der signifikante Teil auf die Länge newSize Zellen gebracht.
    public static void resize(BigNumber a, short newSize) throws Exception {
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
    		// denke, dass damit die length des array gemeint ist
    		expand(a,newSize);
    	}
    	
    }
    
    
    
	//Es kann sein, dass expand() das Array auch physikalisch verlängern
	//muss. Dann kann die Speichergröße verschiedener BigInt-Objekte
	//unterschiedlich sein.
	
	// denke, dass damit die length des array gemeint ist
    public static void expand(BigNumber a, short newSize) throws Exception
    {
       	 a.spart = newSize;
       	 
    	 
//    	 setzt die neuen signifikanten Zellen auf 0.
    	 //wurde hier (noch nicht) gemacht, da ja eig standart 0 bei int
    	 
    	 if(a.value.length<=newSize)
    	 {
    		 throw new Exception("Array muss physikalisch verlängert werden");
    	 }
    }
    
    
    
//    Dies ist eine vereinfachte Addition mit einer Zelle b, deren
//    Wert die volle Registerbreite – z.B. nach einer Multiplikation -
//    haben kann.
    /**
     * 
     * @param c result
     * @param a
     * @param b
     */
    public static void _add(BigNumber c, BigNumber a, BigNumber b, signt s) {
        // no processing of the signs, all values are interpreted to be positive
        
    	int tmp, over =0;
    	sameSize(a,b);
    	try {
        	if(c.value[c.spart] != 0)
        	{
        		System.out.println("hi");
        		resize(c,(short) (a.spart+1));
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
    	
    	c.value[a.spart] = over;
    	c.sign = s;
		reduce(c);

    }
    
    private static void sameSize(BigNumber c, BigNumber a, BigNumber b) {
        // c:= a OP b;

    }
    
    
    
    
    private static void sameSize(BigNumber a, BigNumber b) {
		try {
	    	if(a.spart>b.spart)
	    	{
				resize(b, a.spart);
	    		return;
	    	}
	    	if(b.spart>a.spart)
	    	{
	    		resize(a,b.spart);
	    		return;
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
        		resize(c, (short) (a.spart+1));
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
    				_sub(c,b,a, signt.negative);
    			else
    				_sub(c,a,b, signt.positive);
    			break;
    		case np:
    			_add(c,a,b,signt.negative);
    			break;
    		case pn:
    			_add(c,a,b,signt.positive);
    			break;
    		case nn:
    			if(_le(a, b))
    				_sub(c,b,a,signt.positive);
    			else
    				_sub(c,a,b,signt.negative);
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
    public static void _addInt(BigNumber c, short b) {
        

    	int tmp, over =0;
    	try {
        	if(c.value[c.spart] != 0)
        	{
        		System.out.println("hi");
        		resize(c,(short) (c.spart+1));
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	for(int i =0; i<=c.spart-1; i++)
    	{
    		
    		
    		tmp = c.value[i] + b + over;
    		b = 0;
    		c.value[i]= (int)(tmp & CELL_MASK);
    		over =  (short)((tmp>>CELL_SIZE) & CELL_MASK);
    	}
    	
    	c.value[c.spart] = over;
    	reduce(c);
    }
    
    // return true if a lessequals b
    // return false if a not lessquals b
    private static boolean _le(BigNumber a, BigNumber b) {
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
    
    
    public static boolean checkIfZero(BigNumber a)
    {
    	if(a.spart == 1 && a.value[a.spart-1] == 0)
    		return true;
    	else
    		return false;
    }
    


}
