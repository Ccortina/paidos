import com.carloscortina.demo.model.User;


public interface UserService {

	User getUser(int id);
	
	int createUser(User user);
}
