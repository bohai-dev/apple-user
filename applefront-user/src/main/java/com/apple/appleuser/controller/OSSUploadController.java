package com.apple.appleuser.controller;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.apple.appleuser.dao.TeaGlobalTokenMapper;
import com.apple.appleuser.domain.TeaGlobalToken;
import com.apple.appleuser.exception.MilkTeaException;
import com.apple.appleuser.vo.ResponseBody;

@Controller
public class OSSUploadController {
	
    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    private static String accessKeyId = "LTAIJsHDuEGbjkTy";

    private static String accessKeySecret = "ggk7r1WoKRSofcJmgzrql3nsuLvHrn";

    private static String bucketName = "teamilk";

    //OSS服务器上的文件名
    private  String key;

    private  String uploadFile;



	@Autowired
    private TeaGlobalTokenMapper teaGlobalTokenMapper;


	@RequestMapping(value="/insertOSS", method = RequestMethod.GET)
    public ResponseBody<String> insertOSS(@RequestParam("mediaId") String mediaId) throws MilkTeaException{
       
		ResponseBody<String> responseBody = new ResponseBody<String>();
		
		
		//get access_token
		TeaGlobalToken teaGlobalToken = new TeaGlobalToken();
		teaGlobalToken = teaGlobalTokenMapper.getGlobalToken();
		
		
		//GET图片地址
		//格式后台拼接http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
		String urlList = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
		urlList = urlList + teaGlobalToken.getToken();
		urlList = urlList + "&media_id=" + mediaId;
		
		String osspath = "c:/ossupload/" + mediaId;
		
		
//		int returni = downloadPicture(urlList,osspath);
//		if(returni == 0){
//			
//			responseBody.setData(uploadOSS(mediaId,osspath));
//		}
		URL url;
		try {
			url = new URL(urlList);
			FileUtils.copyURLToFile(url, new File(osspath));
			responseBody.setData(uploadOSS(mediaId,osspath));
			FileUtils.deleteDirectory(new File(osspath));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		

       return responseBody;

    }
	
	//链接url下载图片

    private static int downloadPicture(String urlList,String path) {

    	
        URL url = null;

        try {

            url = new URL(urlList);

            DataInputStream dataInputStream = new DataInputStream(url.openStream());

 

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));

            ByteArrayOutputStream output = new ByteArrayOutputStream();

 

            byte[] buffer = new byte[1024];

            int length;

 

            while ((length = dataInputStream.read(buffer)) > 0) {

                output.write(buffer, 0, length);

            }

            fileOutputStream.write(output.toByteArray());

            dataInputStream.close();

            fileOutputStream.close();

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return 1;

        } catch (IOException e) {

            e.printStackTrace();
            return 2;

        }
		return 0;

    }
    
     String uploadOSS(String key,String uploadFile) {
    	 
    	 OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

         String retStr = "";

         try {

             UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);

             // The local file to upload---it must exist.

             uploadFileRequest.setUploadFile(uploadFile);

             // Sets the concurrent upload task number to 5.

             uploadFileRequest.setTaskNum(5);

             // Sets the part size to 1MB.

             uploadFileRequest.setPartSize(1024 * 1024 * 1);

             // Enables the checkpoint file. By default it's off.

             uploadFileRequest.setEnableCheckpoint(true);

             

             UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

             

             CompleteMultipartUploadResult multipartUploadResult = 

                     uploadResult.getMultipartUploadResult();

             System.out.println(multipartUploadResult.getETag());
             
             retStr = multipartUploadResult.getETag();

             

         } catch (OSSException oe) {

             System.out.println("Caught an OSSException, which means your request made it to OSS, "

                     + "but was rejected with an error response for some reason.");

             System.out.println("Error Message: " + oe.getErrorCode());

             System.out.println("Error Code:       " + oe.getErrorCode());

             System.out.println("Request ID:      " + oe.getRequestId());

             System.out.println("Host ID:           " + oe.getHostId());

         } catch (ClientException ce) {

             System.out.println("Caught an ClientException, which means the client encountered "

                     + "a serious internal problem while trying to communicate with OSS, "

                     + "such as not being able to access the network.");

             System.out.println("Error Message: " + ce.getMessage());

         } catch (Throwable e) {

             e.printStackTrace();

         } finally {

             ossClient.shutdown();

         }
		return retStr;
    
    }


}
