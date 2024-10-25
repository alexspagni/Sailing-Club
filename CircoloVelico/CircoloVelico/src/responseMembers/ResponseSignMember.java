package responseMembers;


import java.io.Serializable;

/**
 * The {@code ResponseSignMember} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an object that conatains the id of the Member who Signed up/in<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseSignMember implements Serializable 
{
  private static final long serialVersionUID = 1L;
  private final String username ;
  private final String password ;
  private final int req;
  private final int id;
/**
 * Class constructor
 * @param username username of the user who signed in or up
 * @param password password of the user who signed in or up
 * @param req an integer value that distinguish which request client has sent to the server.
 * @param id id of the user who signed in or up
 */
  public ResponseSignMember(final String username,final String password,final int req,int id)
  {
    this.username=username;
    this.password=password;
    this.req=req;
    this.id=id;
  }
  /**
 * @return
 *  it return the username of a member
 */
public String getUsername()
  {
    return this.username;
  }
  /**
 * @return
 * it return the password of a member
 */
public String getPassword()
  {
    return this.password;
  }
  /**
 * @return
 * it return an integer value
 */
public int getReq() {
	  return this.req;
  }
  /**
 * @return
 * it return the id of a member
 */
public int getId() {
	  return this.id;
  }
}
