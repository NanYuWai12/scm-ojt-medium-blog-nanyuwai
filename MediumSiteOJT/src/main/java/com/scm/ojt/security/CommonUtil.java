package com.scm.ojt.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scm.ojt.bl.dto.login.ValidationFailResponseDTO;

@Component
public class CommonUtil {
    
    public static final String initVector = "encryptionIntVec";
    
    public static final String strKey = "aesEncryptionKey";
    
    public static final String cipher = "AES";

    /**
     * <h2>errorList</h2>
     * <p>
     * error List
     * </p>
     */
    private static Map<String, String> errorList = new HashMap<String, String>();

    /**
     * <h2>getValidationFailResponse</h2>
     * <p>
     * Get validation fail response
     * </p>
     * 
     * @param br
     * @return ValidationFailResponseDTO
     */
    public ValidationFailResponseDTO getValidationFailResponse(BindingResult br)
            throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        for (FieldError e : br.getFieldErrors()) {
            sb.append(e.getField()).append(":").append(e.getDefaultMessage()).append(",");
        }

        ValidationFailResponseDTO response = new ValidationFailResponseDTO();
        response.setTimeStamp(new Date());
        response.setResponseCode(101);
        response.setResponseDescription("Validation Fail!");
//        response.setErrors(splitToMap(sb.substring(0, sb.length() - 1)));
        return response;
    }

    /**
     * <h2>getNoEmployeeDataById</h2>
     * <p>
     * get No Employee Data By Id
     * </p>
     * 
     * @param loginDto
     * @return ValidationFailResponseDTO
     */
    public ValidationFailResponseDTO getNoEmployeeDataById(String loginDto) {
        errorList.put("1", "Bad Request");
        ValidationFailResponseDTO response = new ValidationFailResponseDTO();
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseDescription(
                "This " + "(" + loginDto + ")" + " employee doesn't exist!");
        response.setErrors(errorList);
        return response;
    }
    
    public static String encodePassword(String password) {
        String encode = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes("UTF-8"), cipher);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec, iv);
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            encode = new String(Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encode;
    }
    
    public static String decodePassword(String password) {
        String decode = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes("UTF-8"), cipher);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec, iv);
            byte[] encryptedText = Base64.decodeBase64(password.getBytes());
            byte[] decrypted = cipher.doFinal(encryptedText);
            decode = new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decode;
    }


//    public Map<String, String> splitToMap(String str){
//        return Splitter.on(",").withKeyValueSeparator(":").split(str);
//    }
}
