package file;

public class TestFile {
    //
    // Java 11
    //
    // void canReadString() throws URISyntaxException, IOException {
    // URI txtFileUri = getClass().getClassLoader()
    // .getResource("helloworld.txt").toURI();
    // String content = Files.readString(Path.of(txtFileUri),
    // Charset.defaultCharset());
    // assertEquals("Hello World!", content);
    // }
    //
    // void canWriteString() throws IOException {
    // Path tmpFilePath = Path.of(
    // File.createTempFile("tempFile", ".tmp").toURI());
    // Path returnedFilePath = Files.writeString(tmpFilePath,
    // "Hello World!", Charset.defaultCharset(),
    // StandardOpenOption.WRITE);
    // assertEquals(tmpFilePath, returnedFilePath);
    // }
}
