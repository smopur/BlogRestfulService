package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/api")
public class BlogController {

    //private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final Map<Integer, Object> blogMap = new HashMap<Integer,Object>();
    
    @RequestMapping(value = "/blog", method=RequestMethod.POST)
    @ResponseStatus (HttpStatus.CREATED)
    @Produces (MediaType.APPLICATION_JSON)
    public void postBlog(@RequestBody BlogEntity blog, HttpServletRequest request, HttpServletResponse response ) {
    	System.out.println(blog.getTitle());
    	ServletContext context = request.getSession().getServletContext();
    	int blogId = (int) counter.incrementAndGet();
    	BlogEntity blogEntity = new BlogEntity();
    	blogEntity.setTitle(blog.getTitle());
    	blogEntity.setTags(blog.getTags());
    	blogEntity.setContent(blog.getContent());
    	blogMap.put(blogId, blogEntity);
    	System.out.println("Blog Map saving: " + blogMap.toString());
    	//Saving the HashMap in the context to retrieve it later in the GET method
    	context.setAttribute("blogMap",blogMap);
    	//Here you can send it to the DB or NoSQL and store it
    	response.setHeader("BlogId",(String.valueOf(blogId)));
        
    }
    
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    @Produces (MediaType.APPLICATION_JSON)
    public ResponseEntity<?>  getBlog (@PathVariable String id, 
            HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("inside get method: " + id);
    	ServletContext context = request.getSession().getServletContext();
    	//Below trying to retrieve it from the context which was saved in the POST
    	Map<Integer, Object> attribute = (Map<Integer, Object>) context.getAttribute("blogMap");

        //loop a Map

        for (Entry<Integer, Object> entry :  attribute.entrySet()) {

        System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

        }
    	System.out.println("Blog Map retrieving: " + blogMap.toString());
    	//Here based on the Id received we can pass the id and retrieve it from the No-SQL or DB 
    	BlogEntity greeting = null;
    	//Retrieving it from the HashMap which I tried to store it in the POST request
    	if(blogMap.containsKey(id)){
    		System.out.println("record found");
    		 greeting = (BlogEntity) blogMap.get(id);
    	}
    	else
    	{
    		System.out.println("no record ");
    		return new ResponseEntity( HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<BlogEntity>(greeting, HttpStatus.OK);
    }
    
    
    
}
