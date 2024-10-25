package requestStaff;


import java.io.Serializable;
/**
 * The {@code RequestStaffSignUp} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Sign up a member of the staff.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestStaffSignUp implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String username;
  private final String pass;
  private final int req;
/**
 * Class constructor
 * @param username username of the staff member.
 * @param password password of the staff member.
 * @param req an integer value that distinguish which request client has sent to the server.
 */
  public RequestStaffSignUp(final String username,final String password,final int req)
  {
    this.username = username;
    this.pass=password;
    this.req=req;
  }


  /**
 * @return
 *  It returns the username of a staff member
 */
public String getUsername()
  {
    return this.username;
    
  }
  /**
 * @return
 *  It returns the password of a staff member
 */
public String getPassword()
  {
    return this.pass;
    
  }
  /**
 * @return
 *  It returns an integer value
 */
public int getTypeReq()
  {
    return this.req;
    
  }
}
