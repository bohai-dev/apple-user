package com.apple.appleuser.task;

/*

 * Licensed to the Apache Software Foundation (ASF) under one

 * or more contributor license agreements.  See the NOTICE file

 * distributed with this work for additional information

 * regarding copyright ownership.  The ASF licenses this file

 * to you under the Apache License, Version 2.0 (the

 * "License"); you may not use this file except in compliance

 * with the License.  You may obtain a copy of the License at

 *

 *     http://www.apache.org/licenses/LICENSE-2.0

 *

 * Unless required by applicable law or agreed to in writing,

 * software distributed under the License is distributed on an

 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY

 * KIND, either express or implied.  See the License for the

 * specific language governing permissions and limitations

 * under the License.

 */





import java.io.IOException;



import com.aliyun.oss.ClientException;

import com.aliyun.oss.OSS;

import com.aliyun.oss.OSSClientBuilder;

import com.aliyun.oss.OSSException;

import com.aliyun.oss.model.CompleteMultipartUploadResult;

import com.aliyun.oss.model.UploadFileRequest;

import com.aliyun.oss.model.UploadFileResult;



/**

 * Examples of uploading with enabling checkpoint file.

 *

 */

public class UploadSample {

    

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    private static String accessKeyId = "LTAIJsHDuEGbjkTy";

    private static String accessKeySecret = "ggk7r1WoKRSofcJmgzrql3nsuLvHrn";

    private static String bucketName = "teamilk";

    //OSS服务器上的文件名
    private static String key = "EGroBdK6g7dMdccKaGkjHICLTDPL2zXZYObQ3FU5UD8XlrV5LLCh06qircspCROb";

    private static String uploadFile = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=15_PkVIjVdB-lDpf07uaCn0nHTvXt6_i_ESFGOFG7pvptiRK-GLho4erXc9MocOmzMLY0ZDOta4NPenTqMR84-dZVrsbNwlg47nKnDMVJrwAT6Ze6M1RLq2JKzgQUJeHgli8wqibX_9rp6pGhMFFDLiABAMKS&media_id=EGroBdK6g7dMdccKaGkjHICLTDPL2zXZYObQ3FU5UD8XlrV5LLCh06qircspCROb";



    public static void main(String[] args) throws IOException {        



        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        

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

    }

}