package com.lecheng.hello.thirdapp.Http;

/**
 * Created by 乐城 on 2017/3/14.
 */

public class WebService {}
//    public static SoapObject method(final String methodName, String[] array) throws Exception {
//        String NAMESPACE = "http://xml.apache.org/xml-soap";
//        String URL = "http://10.0.110.114:8090/web/services/AndroidController?wsdl";//121.42.56.143:8080
//        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
//        /* soapObject.addProperty("username",name);
//        soapObject.addProperty("password",password);*/
//        int i = 0;
//        for (String anArray : array) {
//            soapObject.addProperty("hello" + i, anArray);
//            i++;
//            Log.d("vivi", anArray);
//        }
//        String soapAction = NAMESPACE + "/" + methodName;// SOAP Action
//        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        envelope.bodyOut = soapObject;
//        envelope.dotNet = true;
//        HttpTransportSE ht = new HttpTransportSE(URL);
//        ht.debug = true;
//        envelope.setOutputSoapObject(soapObject);
//        ht.call(soapAction, envelope);
//        Log.d("vivi", envelope.bodyIn.toString() + " hahah");
//        if (envelope.getResponse() != null) {
//            return (SoapObject) envelope.bodyIn;
//        } else {
//            return null;
//        }
//    }
//}
