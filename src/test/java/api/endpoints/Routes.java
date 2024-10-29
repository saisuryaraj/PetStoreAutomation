package api.endpoints;

/*https://petstore.swagger.io/v2/user
create(post):https://petstore.swagger.io/v2/user/{username}
get(get):https://petstore.swagger.io/v2/user/{username}
Update(put):https://petstore.swagger.io/v2/user{username}
Delete(delete):https://petstore.swagger.io/v2/user{username}
*/
public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	//USer model
	public static String post_url=base_url + "/user";
	public static String get_url=base_url + "/user/{username}";
	public static String put_url=base_url + "/user/{username}";
	public static String delete_url=base_url + "/user/{username}";
	
	
}
