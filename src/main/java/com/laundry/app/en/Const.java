package com.laundry.app.en;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
public class Const {
    // Transaction Progress
    public static final String STARTING = "akan_dicuci";
    public static final String STARTED = "sedang_dicuci";
    public static final String FINISHED_WASHING = "selesai_dicuci";
    public static final String FINISHED_IRONING = "selesai_disetrika";
    public static final String READY = "siap_diantar";

    // Transaction Status
    public static final String DONE = "done";
    public static final String WIP = "work_in_progress";

    // Delivery Type
    public static final String DELIVERY_DIANTAR = "diantar";
    public static final String DELIVERY_AMBIL_SENDIRI = "ambil_sendiri";

    // Deposit Laundry Type
    public static final String DEPOSIT_DIJEMPUT = "dijemput";
    public static final String DEPOSIT_ANTAR_SENDIRI = "antar_sendiri";

    // Payment Type
    public static final String CASH = "cash";
    public static final String TRANSFER = "transfer";

    // Kategori
    public static final String PER_PIECE = "per_piece";
    public static final String PER_KILO = "per_kilo";

    // Transaction Type
    public static final String REGULAR = "paket_regular";
    public static final String CEPAT = "paket_cepat";

    // Roles / buat jaga2
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    public static final long ZERO = 0L;
}
