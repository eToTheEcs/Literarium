package literarium.parsingData.parseType;

public final class AuthorInfo {

    private final Integer id;
    private final String name;

    public AuthorInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
