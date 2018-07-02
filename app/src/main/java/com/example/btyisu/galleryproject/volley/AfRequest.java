//package com.example.btyisu.galleryproject.Volley;
//
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.android.volley.*;
//import com.android.volley.Response.ErrorListener;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import kr.co.nowcom.core.NConstants;
//import kr.co.nowcom.core.utils.NDeviceUtils;
//import kr.co.nowcom.core.utils.NNetworkUtils;
//import kr.co.nowcom.mobile.afreeca.common.login.LoginPreference;
//import kr.co.nowcom.mobile.afreeca.global.GlobalChecker;
//import kr.co.nowcom.mobile.afreeca.setting.watch.*;
//
//
//public abstract class AfRequest<T> extends Request<T> {
//    private Context mContext;
//    public AfRequest(Context context, String url, ErrorListener listener) {
//        super(url, listener);
//        mContext = context;
//    }
//    public AfRequest(Context context, int method, String url, ErrorListener listener) {
//        super(method, url, listener);
//        mContext = context;
//    }
//    public AfRequest(Context context, int method, String url, ErrorListener listener, DefaultRetryPolicy retryPolicy) {
//        super(method, url, listener, retryPolicy);
//        mContext = context;
//    }
//
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> headers = new HashMap<String, String>();
//
//        headers.put("User-Agent", NNetworkUtils.getUserAgent(mContext));
//        headers.put("Referer", "http://android.afreecatv.com");
//
//
//        headers.put("Accept-Language", GlobalChecker.getLocaleString());
//
//        StringBuffer sb = new StringBuffer("__uuid=" + NDeviceUtils.getDeviceUUIdTest(mContext));
//
//        String cookie = LoginPreference.getCookie(mContext);
//
//        if(TextUtils.isEmpty(cookie) == false){
//            sb.append(";PdboxTicket="+cookie);
//            if(NConstants.DEBUG){
//                String deveoperId = WatchSettingPreference.getDeveloperWebviewId(mContext);
//                if(TextUtils.isEmpty(deveoperId) == false){
//                    sb.append(";developer=" + deveoperId);
//                }
//            }
//        }
//        headers.put("Cookie", sb.toString());
//
//        return headers;
//    }
//
//    /**
//     * 파라메터에 null값이 들어가 있으면 volley request시 null에러 나는 경우가 있어 이 함수로 체크하여 파라메터 리턴
//     * @Method  : checkParams
//     * @Author  : hjkim
//     * @Since   : 2014. 10. 23. 오후 4:26:59
//     * @param map
//     * @return
//     */
//    public Map<String, String> checkParams(Map<String, String> map){
//        Iterator<Entry<String, String>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Entry<String, String> pairs = (Entry<String, String>)it.next();
//            if(pairs.getValue()==null){
//                map.put(pairs.getKey(), "");
//            }
//        }
//        return map;
//    }
//
//
//
//
//}
