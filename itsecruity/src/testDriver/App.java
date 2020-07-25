package testDriver;

public final class App {
    /**
     * Disable any inheritance.
     */
    private App() { }
    /**
     *
     * @param args The arguments of the program.
     */
    public static void main(final String[] args) {
        FileNameInPackage fn= new FileNameInPackage();
        String relativPath = fn.getFileName("bla.txt");
        IDataSource ds= new DataFile(relativPath);
        while(!ds.eoi()) {
            String ln= ds.readLineAsString();
            System.out.print(ln+"\n");
        }
    }
}
