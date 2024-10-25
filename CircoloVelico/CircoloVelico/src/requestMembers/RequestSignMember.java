package requestMembers;


import java.io.Serializable;

/**
 * The {@code RequestSignMember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Sign in a Member.<br>
 * 2)Sign Up a Member<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestSignMember implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String username;
  private final String pass;
  private String fiscalCode;
  private String address;
  private final int req;
/**
 * Class constructor.
 * @param username username of the member.
 * @param password password of the member.
 * @param req an integer value that distinguish which request client has sent to the server.
 * @param fiscalCode fiscal code of the member.
 * @param address address of the user.
 */
  public RequestSignMember(final String username,final String password,final int req,String fiscalCode,String address)
  {
    this.username = username;
    this.pass=password;
    this.req=req;
    this.fiscalCode=fiscalCode;
    this.address=address;
  }
  /**
   * Class constructor.
   * @param username username of the member.
   * @param password password of the member.
   * @param req an integer value that distinguish which request client has sent to the server.
   */
  public RequestSignMember(final String username,final String password,final int req)
  {
    this.username = username;
    this.pass=password;
    this.req=req;
    this.fiscalCode=" ";
    this.address=" ";
  }

  /**
 * @return
 *  It returns a username of a member
 */
public String getUsername()
  {
    return this.username;
    
  }
  /**
 * @return
 *  It returns a member password
 */
public String getPassword()
  {
    return this.pass;
    
  }
  /**
 * @return
 *  It returns an integer value
 */
public int getReq()
  {
    return this.req;
    
  }
  /**
 * @return
 *  It returns a fiscal code of a member
 */
public String getFiscalCode()
  {
    return this.fiscalCode;
    
  }
  /**
 * @return
 *  It returns the address of a member
 */
public String getAddress()
  {
    return this.address;
    
  }
}
