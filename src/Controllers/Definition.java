package Controllers;
/**
 * This record is used to simply store data, with getters pos() and meaning()
 * implemented by default since it is a Record.
 * @param pos an absolute URL giving the base location of the image
 * @param meaning the location of the image, relative to the url argument
 * @see Builder.Data for usage
 */
public record Definition(String pos, String meaning) {}
