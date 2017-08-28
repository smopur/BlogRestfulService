package hello;

public class BlogEntity {

	private long id;
    private String content;
    private String title;
    private String[]  tags;
    public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getTitle() {
		return title;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	

    

}
