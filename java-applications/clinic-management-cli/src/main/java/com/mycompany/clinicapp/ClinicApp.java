package com.mycompany.clinicapp;

import java.io.*;
import java.util.*;

public class ClinicApp {

    static Scanner in = new Scanner(System.in);

    // أسماء الملفات
    static String f_users = "users.txt";
    static String f_patients = "patients.txt";
    static String f_doctors = "doctors.txt";
    static String f_meds = "meds.txt";
    static String f_depts = "depts.txt";
    static String f_rooms = "rooms.txt";
    static String f_finlog = "finance_log.txt";
    static String f_phlog = "pharmacy_log.txt";

    public static void main(String[] args) {
        initFiles();
        while (true) {
            System.out.println("\n شاشة الدخول ");
            System.out.print("user: ");
            String u = in.nextLine().trim();
            System.out.print("pass: ");
            String p = in.nextLine().trim();
            Map<String, String> info = auth(u, p);
            if (info == null) {
                System.out.println("بيانات خطأ");
                continue;
            }
            String role = info.get("role");
            if (role.equals("admin")) {
                menu_admin();
            } else if (role.equals("reception")) {
                menu_reception();
            } else if (role.equals("doctor")) {
                menu_doctor(info.get("docId"));
            } else if (role.equals("finance")) {
                menu_finance();
            } else if (role.equals("pharmacy")) {
                menu_pharmacy();
            }
        }
    }

    static void initFiles() {
        try {
            if (!new File(f_users).exists()) {
                List<String> x = new ArrayList<>();
                x.add("admin,123,admin");
                x.add("rec,123,reception");
                x.add("doc1,123,doctor,d1,heart");
                x.add("fin,123,finance");
                x.add("ph,123,pharmacy");
                writeAll(f_users, x);
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_doctors).exists()) {
                List<String> x = new ArrayList<>();
                x.add("d1,Ahmed,heart,0");
                x.add("d2,Mona,eyes,0");
                writeAll(f_doctors, x);
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_depts).exists()) {
                writeAll(f_depts, Arrays.asList("heart", "eyes", "bones"));
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_rooms).exists()) {
                writeAll(f_rooms, Arrays.asList("r1", "r2", "r3"));
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_meds).exists()) {
                List<String> x = new ArrayList<>();
                x.add("panadol,100,5");
                x.add("augmentin,50,12");
                x.add("vitC,80,3");
                writeAll(f_meds, x);
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_patients).exists()) {
                writeAll(f_patients, new ArrayList<>());
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_finlog).exists()) {
                writeAll(f_finlog, new ArrayList<>());
            }
        } catch (Exception e) {
        }
        try {
            if (!new File(f_phlog).exists()) {
                writeAll(f_phlog, new ArrayList<>());
            }
        } catch (Exception e) {
        }
    }


    static Map<String, String> auth(String u, String p) {
        for (String s : readAll(f_users)) {
            String[] a = s.split(",");
            if (a.length < 3) {
                continue;
            }
            if (a[0].equals(u) && a[1].equals(p)) {
                Map<String, String> m = new HashMap<>();
                m.put("role", a[2]);
                String docId = a.length > 3 ? a[3] : "-";
                String dept = a.length > 4 ? a[4] : "-";
                m.put("docId", docId);
                m.put("dept", dept);
                return m;
            }
        }
        return null;
    }

    static void menu_reception() {
        while (true) {
            System.out.println("\n-- الاستقبال --");
            System.out.println("1- خدمه مريض");
            System.out.println("2- رجوع للدخول");
            System.out.print("اختيار: ");
            String c = in.nextLine();
            if (c.equals("1")) {
                reception_service();
            } else if (c.equals("2")) {
                return;
            }
        }
    }

    static void reception_service() {
        System.out.print("رقم هوية المريض: ");
        String pid = in.nextLine().trim();
        String line = findPatient(pid);
        String name;
        if (line == null) {
            System.out.print("اسم المريض جديد: ");
            name = in.nextLine().trim();
        } else {
            name = line.split(",")[1];
            System.out.println("المريض موجود: " + name);
        }
        String dept = chooseFromFile("اختر القسم", f_depts);
        if (dept == null) {
            System.out.println("تم الالغاء");
            return;
        }
        String docId = pickDoctorForDept(dept);
        if (docId == null) {
            System.out.println("لا يوجد دكتور متاح الآن");
            return;
        }
        if (line == null) {
            String rec = String.join(",", Arrays.asList(pid, name, dept, docId, "-", "waiting", "0", "-"));
            appendLine(f_patients, rec);
        } else {
            updatePatient(pid, (arr) -> {
                arr[2] = dept;
                arr[3] = docId;
                arr[5] = "waiting";
                return arr;
            });
        }
        System.out.println("تم توجيه المريض للدكتور: " + docId);
    }

    static String pickDoctorForDept(String dept) {
        List<String> ds = readAll(f_doctors);
        String chosen = null;
        int minCnt = Integer.MAX_VALUE;
        for (String s : ds) {
            String[] a = s.split(",");
            if (a.length < 4) {
                continue;
            }
            if (!a[2].equals(dept)) {
                continue;
            }
            if (a[3].equals("1")) {
                continue; 
            }
            int cnt = 0;
            for (String p : readAll(f_patients)) {
                String[] pp = p.split(",");
                if (pp.length > 3 && pp[3].equals(a[0]) && pp[5].equals("waiting")) {
                    cnt++;
                }
            }
            if (cnt < minCnt) {
                minCnt = cnt;
                chosen = a[0];
            }
        }
        return chosen;
    }

    static void menu_doctor(String docId) {
        while (true) {
            System.out.println("\n-- الدكتور -- (" + docId + ")");
            System.out.println("1- خدمه مريض");
            System.out.println("2- عرض جميع الادوية");
            System.out.println("3- تحديث الحالة (مشغول/متاح)");
            System.out.println("4- رجوع للدخول");
            System.out.print("اختيار: ");
            String c = in.nextLine();
            if (c.equals("1")) {
                doctor_service(docId);
            } else if (c.equals("2")) {
                printFile(f_meds);
            } else if (c.equals("3")) {
                toggleDoctorBusy(docId);
            } else if (c.equals("4")) {
                return;
            }
        }
    }

    static void doctor_service(String docId) {
        List<String> waiting = new ArrayList<>();
        for (String p : readAll(f_patients)) {
            String[] a = p.split(",");
            if (a.length > 5 && a[3].equals(docId) && a[5].equals("waiting")) {
                waiting.add(p);
            }
        }
        if (waiting.isEmpty()) {
            System.out.println("لا يوجد مرضى");
            return;
        }
        System.out.println("مرضى بانتظارك:");
        for (String s : waiting) {
            String[] a = s.split(",");
            System.out.println(a[0] + " - " + a[1]);
        }
        System.out.print("ادخل هوية المريض: ");
        String pid = in.nextLine().trim();
        String line = findPatient(pid);
        if (line == null) {
            System.out.println("غير موجود");
            return;
        }
        System.out.print("التشخيص: ");
        String dx = in.nextLine();
        List<String> chosen = new ArrayList<>();
        System.out.print("كم عدد الادوية تريد اضافتها: ");
        int n = parseIntSafe(in.nextLine());
        List<String> meds = readAll(f_meds);
        for (int i = 0; i < n; i++) {
            System.out.println("-- قائمة الادوية --");
            for (int j = 0; j < meds.size(); j++) {
                String[] m = meds.get(j).split(",");
                System.out.println((j + 1) + ") " + m[0] + " (متاح=" + m[1] + ", سعر=" + m[2] + ")");
            }
            System.out.print("اختر رقم الدواء: ");
            int k = parseIntSafe(in.nextLine()) - 1;
            if (k < 0 || k >= meds.size()) {
                System.out.println("خاطئ");
                i--;
                continue;
            }
            String[] mm = meds.get(k).split(",");
            System.out.print("الكمية: ");
            int q = parseIntSafe(in.nextLine());
            chosen.add(mm[0] + ":" + q);
        }
        String medsStr = String.join(";", chosen);
        updatePatient(pid, (arr) -> {
            arr[4] = dx;
            arr[7] = medsStr;
            arr[5] = "diagnosed";
            return arr;
        });
        System.out.println("تم حفظ الوصفة");
    }

    static void toggleDoctorBusy(String docId) {
        List<String> x = readAll(f_doctors);
        List<String> y = new ArrayList<>();
        for (String s : x) {
            String[] a = s.split(",");
            if (a[0].equals(docId)) {
                a[3] = a[3].equals("1") ? "0" : "1";
                s = String.join(",", a);
            }
            y.add(s);
        }
        writeAll(f_doctors, y);
        System.out.println("تم التغيير");
    }

    static void menu_finance() {
        while (true) {
            System.out.println("\n-- المالية --");
            System.out.println("1- خدمه مريض (حساب ودفع)");
            System.out.println("2- عرض جميع المرضى اليوم (من السجل)");
            System.out.println("3- إظهار احصائيات بسيطة");
            System.out.println("4- رجوع للدخول");
            System.out.print("اختيار: ");
            String c = in.nextLine();
            if (c.equals("1")) {
                finance_service();
            } else if (c.equals("2")) {
                printFile(f_finlog);
            } else if (c.equals("3")) {
                finance_stats();
            } else if (c.equals("4")) {
                return;
            }
        }
    }

    static void finance_service() {
        System.out.print("هوية المريض: ");
        String pid = in.nextLine().trim();
        String line = findPatient(pid);
        if (line == null) {
            System.out.println("لا يوجد");
            return;
        }
        String[] a = line.split(",");
        String medsStr = a[7];
        if (medsStr.equals("-") || medsStr.isEmpty()) {
            System.out.println("لا وصفة");
            return;
        }
        int total = 0;
        String[] parts = medsStr.split(";");
        for (String it : parts) {
            String[] kv = it.split(":");
            if (kv.length < 2) {
                continue;
            }
            String m = kv[0];
            int q = parseIntSafe(kv[1]);
            String mline = findMed(m);
            if (mline != null) {
                int price = parseIntSafe(mline.split(",")[2]);
                total += price * q;
            }
        }
        System.out.println("المبلغ المطلوب: " + total);
        System.out.print("ادخل 1 للتأكيد: ");
        String ok = in.nextLine().trim();
        if (!ok.equals("1")) {
            System.out.println("تم الالغاء");
            return;
        }
        updatePatient(pid, (arr) -> {
            arr[6] = "1";
            arr[5] = "paid";
            return arr;
        });
        appendLine(f_finlog, now() + "," + pid + "," + total);
        System.out.println("تم الدفع وتحويل المريض للصيدلية");
    }

    static void finance_stats() {
        int served = 0, sum = 0, items = 0;
        for (String s : readAll(f_finlog)) {
            String[] a = s.split(",");
            if (a.length >= 3) {
                served++;
                sum += parseIntSafe(a[2]);
            }
        }
        for (String p : readAll(f_patients)) {
            String[] a = p.split(",");
            if (a.length >= 8 && !a[7].equals("-")) {
                for (String it : a[7].split(";")) {
                    String[] kv = it.split(":");
                    if (kv.length == 2) {
                        items += parseIntSafe(kv[1]);
                    }
                }
            }
        }
        System.out.println("عدد المرضى الذين خدموا: " + served);
        System.out.println("إجمالي التحصيل: " + sum);
        System.out.println("عدد وحدات الأدوية (تقريبي): " + items);
    }

    static void menu_pharmacy() {
        while (true) {
            System.out.println("\n-- الصيدلية --");
            System.out.println("1- خدمه مريض (صرف)");
            System.out.println("2- عرض المرضى (سجل) اليوم");
            System.out.println("3- عرض كل الادوية");
            System.out.println("4- إضافة دواء جديد");
            System.out.println("5- إحصائيات بسيطة");
            System.out.println("6- رجوع للدخول");
            System.out.print("اختيار: ");
            String c = in.nextLine();
            if (c.equals("1")) {
                pharmacy_service();
            } else if (c.equals("2")) {
                printFile(f_phlog);
            } else if (c.equals("3")) {
                printFile(f_meds);
            } else if (c.equals("4")) {
                add_med();
            } else if (c.equals("5")) {
                pharmacy_stats();
            } else if (c.equals("6")) {
                return;
            }
        }
    }

    static void pharmacy_service() {
        System.out.print("هوية المريض: ");
        String pid = in.nextLine().trim();
        String line = findPatient(pid);
        if (line == null) {
            System.out.println("لا يوجد");
            return;
        }
        String[] a = line.split(",");
        if (!a[6].equals("1")) {
            System.out.println("غير مدفوع، راجع المالية");
            return;
        }
        if (a[7].equals("-") || a[7].isEmpty()) {
            System.out.println("لا يوجد أدوية");
            return;
        }
        System.out.println("بيانات المريض: " + a[0] + " " + a[1] + " تشخيص:" + a[4]);
        System.out.println("الوصفة: " + a[7]);
        // تقليل المخزون
        String[] parts = a[7].split(";");
        List<String> meds = readAll(f_meds);
        List<String> out = new ArrayList<>();
        for (String mline : meds) {
            out.add(mline);
        }
        for (String it : parts) {
            String[] kv = it.split(":");
            if (kv.length < 2) {
                continue;
            }
            String m = kv[0];
            int q = parseIntSafe(kv[1]);
            for (int i = 0; i < out.size(); i++) {
                String[] mm = out.get(i).split(",");
                if (mm[0].equals(m)) {
                    int stock = parseIntSafe(mm[1]);
                    mm[1] = String.valueOf(Math.max(0, stock - q));
                    out.set(i, String.join(",", mm));
                    break;
                }
            }
        }
        writeAll(f_meds, out);
        updatePatient(pid, (arr) -> {
            arr[5] = "done";
            return arr;
        });
        appendLine(f_phlog, now() + "," + pid + "," + a[7]);
        System.out.println("تم الصرف");
    }

    static void add_med() {
        System.out.print("اسم الدواء: ");
        String n = in.nextLine().trim();
        System.out.print("المخزون: ");
        String s = in.nextLine().trim();
        System.out.print("السعر: ");
        String pr = in.nextLine().trim();
        appendLine(f_meds, n + "," + s + "," + pr);
        System.out.println("تم الاضافة");
    }

    static void pharmacy_stats() {
        int served = 0, items = 0;
        for (String s : readAll(f_phlog)) {
            String[] a = s.split(",");
            if (a.length >= 3) {
                served++;
                String meds = a[2];
                for (String it : meds.split(";")) {
                    String[] kv = it.split(":");
                    if (kv.length == 2) {
                        items += parseIntSafe(kv[1]);
                    }
                }
            }
        }
        System.out.println("عدد المرضى المصروف لهم: " + served);
        System.out.println("مجموع العبوات المصروفة: " + items);
    }

    static void menu_admin() {
        while (true) {
            System.out.println("\n-- الادمن --");
            System.out.println("1- مستخدمين (اضافة/حذف/عرض)");
            System.out.println("2- دكاترة (اضافة/حذف/عرض)");
            System.out.println("3- أقسام (اضافة/حذف/عرض)");
            System.out.println("4- غرف (اضافة/حذف/عرض)");
            System.out.println("5- المرضى (عرض بسيط)");
            System.out.println("6- خروج للدخول");
            System.out.print("اختيار: ");
            String c = in.nextLine();
            if (c.equals("1")) {
                crud_users();
            } else if (c.equals("2")) {
                crud_doctors();
            } else if (c.equals("3")) {
                crud_simple(f_depts, "قسم");
            } else if (c.equals("4")) {
                crud_simple(f_rooms, "غرفة");
            } else if (c.equals("5")) {
                printFile(f_patients);
            } else if (c.equals("6")) {
                return;
            }
        }
    }

    static void crud_users() {
        while (true) {
            System.out.println("\n[مستخدمين] 1-اضافة 2-حذف 3-عرض 4-رجوع");
            String c = in.nextLine();
            if (c.equals("1")) {
                System.out.print("user: ");
                String u = in.nextLine();
                System.out.print("pass: ");
                String p = in.nextLine();
                System.out.print("role(admin/reception/doctor/finance/pharmacy): ");
                String r = in.nextLine();
                String docId = "-", dept = "-";
                if (r.equals("doctor")) {
                    System.out.print("رقم الدكتور (جديد أو موجود): ");
                    docId = in.nextLine();
                    System.out.print("قسم الدكتور: ");
                    dept = in.nextLine();
                    // تأكد من وجوده بملف الدكاترة
                    ensureDoctor(docId, "Doctor" + docId, dept);
                }
                appendLine(f_users, u + "," + p + "," + r + "," + docId + "," + dept);
                System.out.println("تم");
            } else if (c.equals("2")) {
                System.out.print("user لحذف: ");
                String u = in.nextLine();
                List<String> x = new ArrayList<>();
                for (String s : readAll(f_users)) {
                    if (!s.split(",")[0].equals(u)) {
                        x.add(s);
                    }
                }
                writeAll(f_users, x);
            } else if (c.equals("3")) {
                printFile(f_users);
            } else if (c.equals("4")) {
                return;
            }
        }
    }

    static void ensureDoctor(String id, String name, String dept) {
        boolean ok = false;
        List<String> x = readAll(f_doctors);
        for (String s : x) {
            if (s.split(",")[0].equals(id)) {
                ok = true;
                break;
            }
        }
        if (!ok) {
            x.add(id + "," + name + "," + dept + ",0");
            writeAll(f_doctors, x);
        }
    }

    static void crud_doctors() {
        while (true) {
            System.out.println("\n[دكاترة] 1-اضافة 2-حذف 3-عرض 4-رجوع");
            String c = in.nextLine();
            if (c.equals("1")) {
                System.out.print("id: ");
                String id = in.nextLine();
                System.out.print("name: ");
                String nm = in.nextLine();
                System.out.print("dept: ");
                String dp = in.nextLine();
                appendLine(f_doctors, id + "," + nm + "," + dp + ",0");
            } else if (c.equals("2")) {
                System.out.print("id لحذف: ");
                String id = in.nextLine();
                List<String> x = new ArrayList<>();
                for (String s : readAll(f_doctors)) {
                    if (!s.split(",")[0].equals(id)) {
                        x.add(s);
                    }
                }
                writeAll(f_doctors, x);
            } else if (c.equals("3")) {
                printFile(f_doctors);
            } else if (c.equals("4")) {
                return;
            }
        }
    }

    static void crud_simple(String file, String label) {
        while (true) {
            System.out.println("\n[" + label + "] 1-اضافة 2-حذف 3-عرض 4-رجوع");
            String c = in.nextLine();
            if (c.equals("1")) {
                System.out.print(label + ": ");
                String v = in.nextLine();
                appendLine(file, v);
            } else if (c.equals("2")) {
                System.out.print("احذف القيمة: ");
                String v = in.nextLine();
                List<String> x = new ArrayList<>();
                for (String s : readAll(file)) {
                    if (!s.equals(v)) {
                        x.add(s);
                    }
                }
                writeAll(file, x);
            } else if (c.equals("3")) {
                printFile(file);
            } else if (c.equals("4")) {
                return;
            }
        }
    }

    static String findPatient(String pid) {
        for (String s : readAll(f_patients)) {
            String[] a = s.split(",");
            if (a.length > 0 && a[0].equals(pid)) {
                return s;
            }
        }
        return null;
    }

    interface ArrEdit {

        String[] edit(String[] a);
    }

    static void updatePatient(String pid, ArrEdit ed) {
        List<String> x = readAll(f_patients);
        List<String> y = new ArrayList<>();
        boolean found = false;
        for (String s : x) {
            String[] a = s.split(",");
            if (a[0].equals(pid)) {
                a = ed.edit(a);
                s = String.join(",", a);
                found = true;
            }
            y.add(s);
        }
        if (found) {
            writeAll(f_patients, y);
        }
    }

    static String findMed(String name) {
        for (String s : readAll(f_meds)) {
            String[] a = s.split(",");
            if (a[0].equals(name)) {
                return s;
            }
        }
        return null;
    }

    static List<String> readAll(String f) {
        List<String> a = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String s;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (!s.isEmpty()) {
                    a.add(s);
                }
            }
        } catch (Exception e) {
        }
        return a;
    }

    static void writeAll(String f, List<String> a) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            for (String s : a) {
                pw.println(s);
            }
        } catch (Exception e) {
            System.out.println("ERR" + e.getMessage());
        }
    }

    static void appendLine(String f, String s) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
            pw.println(s);
        } catch (Exception e) {
            System.out.println("ERR" + e.getMessage());
        }
    }

    static void printFile(String f) {
        List<String> x = readAll(f);
        if (x.isEmpty()) {
            System.out.println("(فارغ)");
        }
        for (String s : x) {
            System.out.println(s);
        }
    }

    static String chooseFromFile(String msg, String f) {
        List<String> x = readAll(f);
        if (x.isEmpty()) {
            System.out.println("لا يوجد");
            return null;
        }
        System.out.println(msg + ":");
        for (int i = 0; i < x.size(); i++) {
            System.out.println((i + 1) + ") " + x.get(i));
        }
        System.out.print("رقم: ");
        int k = parseIntSafe(in.nextLine()) - 1;
        if (k < 0 || k >= x.size()) {
            return null;
        }
        return x.get(k);
    }

    static int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    static String now() {
        return new Date().toString();
    }
}
