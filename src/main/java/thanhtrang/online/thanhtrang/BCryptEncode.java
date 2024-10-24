/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author thanhcom
 */
public class BCryptEncode {
    
    public  String PassEncode(String pass)
    {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, pass.toCharArray());
        return bcryptHashString;
    }
    
     public  boolean PassVerify(String pass_raw,String passbcrypt)
    {
        BCrypt.Result result = BCrypt.verifyer().verify(pass_raw.toCharArray(),passbcrypt);
        return  result.verified;
    }
    
}
