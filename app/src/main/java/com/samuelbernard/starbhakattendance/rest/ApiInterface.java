package com.samuelbernard.starbhakattendance.rest;

import com.samuelbernard.starbhakattendance.model.ResponseLogin;
import com.samuelbernard.starbhakattendance.model.ResponseScan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    // Login User
    @FormUrlEncoded
    @POST("post/login")
    Call<ResponseLogin> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    // Scan Qrcode
    @FormUrlEncoded
    @POST("post/absen")
    Call<ResponseScan> scanQrcode(
            @Field("kategori_presensi") String kategori_presensi,
            @Field("keterangan") String keterangan,
            @Field("id_siswa") String id_siswa,
            @Field("id_kegiatan") String id_kegiatan

    );

//    // Update User
//    @FormUrlEncoded
//    @PUT("api/user/{id}")
//    Call<ResponseUpdateUser> updateUser(
//            @Path("id") String id,
//            @Field("password") String password,
//            @Field("phone_number") String phone_number,
//            @Field("imei") String imei
//    );
//
//    // Check Quarantine
//    @GET("api/karantina/{no_ktp}/{email}/{no_hp}")
//    Call<ResponseBody> getDuplicateData(
//            @Path("no_ktp") String no_ktp,
//            @Path("email") String email,
//            @Path("no_hp") String no_hp
//    );
//
//    // Post Quarantine
//    @FormUrlEncoded
//    @POST("api/karantina/post")
//    Call<ResponseBody> postQuarantine(
//            @Field("salescode") String salescode,
//            @Field("no_ktp") String no_ktp,
//            @Field("no_hp") String no_hp,
//            @Field("email") String email
//    );
//
//    // Sales order
//    @Multipart
//    @POST("api/data/post")
//    Call<ResponseBody> postClosing(
//            @Part("salescode") String salescode,
//            @Part("nama_ktp") String nama_ktp,
//            @Part("no_ktp") String no_ktp,
//            @Part("alamat") String alamat,
//            @Part("kota") String kota,
//            @Part("kel") String kel,
//            @Part("kec") String kec,
//            @Part("rt") String rt,
//            @Part("rw") String rw,
//            @Part("kode_pos") String kode_pos,
//            @Part("tanggal_lahir") String tanggal_lahir,
//            @Part("jenis_kelamin") String jenis_kelamin,
//            @Part("telp_rumah") String telp_rumah,
//            @Part("no_hp") String no_hp,
//            @Part("no_hp_2") String no_hp_2,
//            @Part("email") String email,
//            @Part("stts_rumah") String stts_rumah,
//            @Part("tipe_rumah") String tipe_rumah,
//            @Part("akses_lokasi") String akses_lokasi,
//
//            @Part("alamat_pemasangan") String alamat_pemasangan,
//            @Part("kota_pemasangan") String kota_pemasangan,
//            @Part("kec_pemasangan") String kec_pemasangan,
//            @Part("kel_pemasangan") String kel_pemasangan,
//            @Part("rt_pemasangan") String rt_pemasangan,
//            @Part("rw_pemasangan") String rw_pemasangan,
//            @Part("kodepos_pemasangan") String kodepos_pemasangan,
//
//            @Part("kategori_produk") String kategori_produk,
//            @Part("produk_nama") String produk_nama,
//            @Part("value_pack") String value_pack,
//            @Part("produk_market") String produk_market,
//            @Part("payment") String payment,
//            @Part("bonus") String bonus,
//            @Part("installasi_fee") String installasi_fee,
//            @Part("catatan_pemasangan") String catatan_pemasangan,
//            @Part("total_harga") String total_harga,
//            @Part("long") String longg,
//            @Part("latt") String latt,
//            @Part MultipartBody.Part foto,
//            @Part MultipartBody.Part foto_dua,
//            @Part MultipartBody.Part foto_tiga,
//            @Part("user_id") String user_id,
//            @Part("result_type") String result_type
//    );
//
//    // Canvasing
//    // Thinking
//    @Multipart
//    @POST("api/thinking/post")
//    Call<ResponseBody> postThinking(
//            @Part("user_id") String user_id,
//            @Part("nama") String nama,
//            @Part("phone") String phone,
//            @Part("email") String email,
//            @Part("salescode") String salescode,
//            @Part MultipartBody.Part foto,
//            @Part("long") String longitude,
//            @Part("latt") String latitude
//    );
//
//    // Reject
//    @Multipart
//    @POST("api/reject/post")
//    Call<ResponseBody> postReject(
//            @Part("user_id") String user_id,
//            @Part("salescode") String salescode,
//            @Part MultipartBody.Part foto,
//            @Part("long") String longitude,
//            @Part("latt") String latitude
//    );
//
//    // Sales dashboard daily
//    @GET("api/Getsalesday/{salescode}/{day}")
//    Call<ResponseSalesDashboard> getSalesDayResult(
//            @Path("salescode") String salescode,
//            @Path("day") int day
//    );
//
//    // Sales dashboard monthly
//    @GET("api/Getsalesmonth/{salescode}/{month}")
//    Call<ResponseSalesDashboard> getSalesMonthResult(
//            @Path("salescoding") String salescode,
//            @Path("month") int month
//    );
//
//    // Leader dashboard daily
//    @GET("api/leaderfilterday/{salescode}/{day}")
//    Call<ResponseBody> getDailyTL(
//            @Path("salescode") String salescode,
//            @Path("day") int day
//    );
//
//    // Leader dashboard monthly
//    @GET("api/leaderfiltermonth/{salescode}/{month}")
//    Call<ResponseBody> getMonthTL(
//            @Path("salescode") String salescode,
//            @Path("month") int month
//    );
//
//    // Supervisor dashboard daily
//    @GET("api/supervisorfilter/{supervisorcode}/{daysupervisor}")
//    Call<ResponseBody> getDailySupervisor(
//            @Path("supervisorcode") String supervisorcode,
//            @Path("daysupervisor") int daysupervisor
//    );
//
//    // Supervisor dashboard monthly
//    @GET("api/supervisorfiltermonth/{supervisorcode}/{monthsupervisor}")
//    Call<ResponseBody> getMonthlySupervisor(
//            @Path("supervisorcode") String supervisorcode,
//            @Path("monthsupervisor") int monthsupervisor
//    );
//
//    // Manager dashboard
//    @GET("api/managerfilterday/{managercode}/{managerday}")
//    Call<ResponseBody> getDailyManager(
//            @Path("managercode") String managercode,
//            @Path("managerday") int managerday
//    );
//
//    /* Get manager monthly result*/
//    @GET("api/managerfiltermonth/{managercodemonth}/{managermonth}")
//    Call<ResponseBody> getMonthlyManager(
//            @Path("managercodemonth") String managercodemonth,
//            @Path("managermonth") int managermonth
//    );
//
//    /* Get top 3 sales */
//    @GET("api/topdown/manager")
//    Call<ResponseBody> getTopDown3();
//
//    /* Get top 10 sales */
//    @GET("api/topdown/generalmanager")
//    Call<ResponseBody> getTopDown10();
//
//    /* Get chart national */
//    @GET("api/chart/tahun")
//    Call<List<DataNational>> getNationalData();
//
//    /* Get chart regional */
//    @GET("api/chart/tahun")
//    Call<ResponseBody> getRegionalData();
//
//    /* Send notification to superior */
//    @Headers({"Authorization: key=AAAAiaMlTL0:APA91bGaR87n8dvJR4WST4fWLImI1MLUEe4xD_cYLOS31Duo4HWeh5GKTU7cRW0fsmv79Ox5_dZptTYnWA7XxhJaqLQXhBCA27F-8pn7vOG--M_w90UN5GS2XPflPGzOGRoMXzhbop2t",
//            "Content-Type:application/json"})
//    @POST("fcm/send")
//    Call<ResponseBody> sendChatNotification(@Body RequestNotification requestNotificaton);
//
//    // Get data city
//    @GET("api/kota")
//    Call<ListDistricts> getDataCity();
//
//    // Get data regency
//    @GET("api/kabupaten")
//    Call<ListSubDistricts> getDataRegency();
//
//    // Get data village
//    @GET("api/kelurahan")
//    Call<ListVillage> getVillageOffice();
//
//    // Get all product data
//    @GET("api/productindex")
//    Call<ListProduct> getDataProduct();
//
//    // Get all alacarte data
//    @GET("api/product")
//    Call<ListProduct> getDataAlacarte();
//
//    // Post Check in
//    @Multipart
//    @POST("cekin")
//    Call<ResponseBody> postCheckin(
//            @Field("") String salescode
//    );
//
//    // Update check out
//    @Multipart
//    @PUT("cekout/{id}")
//    Call<ResponseBody> putCheckout(
//            @Path("id") String id,
//            @Field("password") String password,
//            @Field("phone_number") String phone_number,
//            @Field("imei") String imei
//    );
//
//    // TODO update token, get token, get semua sales berdasarkan leader, send token
//
//    @FormUrlEncoded
//    @PUT("api/update/tokens/{id}")
//    Call<LoginUser> updateUserToken(
//            @Path("id") String id,
//            @Field("token") String token
//    );
//
//    @FormUrlEncoded
//    @PUT("api/update/locations/{id}")
//    Call<LoginUser> updateUserLocation(
//            @Path("id") String id,
//            @Field("lat") String lat,
//            @Field("lng") String lng
//    );
//
//    @GET("api/userhi/{id}")
//    Call<ResponseListUser> getListUser(
//            @Path("id") String id
//    );
}