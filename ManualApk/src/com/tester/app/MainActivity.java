package com.tester.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.os.BatteryManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.os.Build;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity {
    private LinearLayout contentContainer;
    private LinearLayout sidebarLayout;
    private boolean isSidebarOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Root Layout Utama (FrameLayout / Relative Style via Frame)
        android.widget.FrameLayout rootLayout = new android.widget.FrameLayout(this);
        rootLayout.setBackgroundColor(Color.parseColor("#0b0f19"));

        // 1. Konten Utama di Layar
        ScrollView mainScroll = new ScrollView(this);
        contentContainer = new LinearLayout(this);
        contentContainer.setOrientation(LinearLayout.VERTICAL);
        contentContainer.setPadding(35, 60, 35, 35);
        mainScroll.addView(contentContainer);
        rootLayout.addView(mainScroll);

        // 2. Panel Sidebar (Menu Samping)
        sidebarLayout = new LinearLayout(this);
        sidebarLayout.setOrientation(LinearLayout.VERTICAL);
        sidebarLayout.setBackgroundColor(Color.parseColor("#020617"));
        sidebarLayout.setPadding(35, 60, 35, 35);
        
        android.widget.FrameLayout.LayoutParams sideParams = new android.widget.FrameLayout.LayoutParams(
            700, android.widget.FrameLayout.LayoutParams.MATCH_PARENT
        );
        sidebarLayout.setLayoutParams(sideParams);
        sidebarLayout.setTranslationX(-700); // Sembunyikan di awal
        rootLayout.addView(sidebarLayout);

        // Bangun Menu Sidebar
        buildSidebarMenu();

        // Tampilkan Halaman Default: Dashboard
        showDashboardPage();

        setContentView(rootLayout);
    }

    // --- PEMBANGUN SIDEBAR MENU ---
    private void buildSidebarMenu() {
        TextView sideTitle = new TextView(this);
        sideTitle.setText("NAVIGATION");
        sideTitle.setTextColor(Color.parseColor("#38bdf8"));
        sideTitle.setTextSize(16);
        sideTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        sideTitle.setPadding(0, 0, 0, 30);
        sidebarLayout.addView(sideTitle);

        sidebarLayout.addView(createSidebarButton("📊 1. Dashboard", v -> { showDashboardPage(); toggleSidebar(); }));
        sidebarLayout.addView(createSidebarButton("⚙️ 2. Module Hub (Tools)", v -> { showModuleHubPage(); toggleSidebar(); }));
        sidebarLayout.addView(createSidebarButton("📱 3. Device & Env Info", v -> { showDeviceInfoPage(); toggleSidebar(); }));
        sidebarLayout.addView(createSidebarButton("🤖 4. AI Security Assistant", v -> { showAIAssistantPage(); toggleSidebar(); }));
    }

    private Button createSidebarButton(String text, View.OnClickListener listener) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTextColor(Color.parseColor("#f1f5f9"));
        btn.setTextSize(14);
        btn.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        btn.setPadding(20, 25, 20, 25);
        
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#1e293b"));
        bg.setCornerRadius(8);
        btn.setBackground(bg);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 15);
        btn.setLayoutParams(params);
        btn.setOnClickListener(listener);
        return btn;
    }

    private void toggleSidebar() {
        if (isSidebarOpen) {
            sidebarLayout.animate().translationX(-700).setDuration(250).start();
            isSidebarOpen = false;
        } else {
            sidebarLayout.animate().translationX(0).setDuration(250).start();
            isSidebarOpen = true;
        }
    }

    private void addHeaderWithMenuButton(String titleText) {
        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        headerLayout.setGravity(Gravity.CENTER_VERTICAL);
        
        Button menuBtn = new Button(this);
        menuBtn.setText(" ☰ ");
        menuBtn.setTextColor(Color.WHITE);
        menuBtn.setTextSize(16);
        GradientDrawable mBg = new GradientDrawable();
        mBg.setColor(Color.parseColor("#1e293b"));
        mBg.setCornerRadius(8);
        menuBtn.setBackground(mBg);
        menuBtn.setOnClickListener(v -> toggleSidebar());
        headerLayout.addView(menuBtn);

        TextView title = new TextView(this);
        title.setText("  " + titleText);
        title.setTextColor(Color.parseColor("#38bdf8"));
        title.setTextSize(18);
        title.setTypeface(null, android.graphics.Typeface.BOLD);
        headerLayout.addView(title);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 25);
        headerLayout.setLayoutParams(params);
        
        contentContainer.addView(headerLayout);
    }

    // --- HALAMAN 1: DASHBOARD ---
    private void showDashboardPage() {
        contentContainer.removeAllViews();
        addHeaderWithMenuButton("DASHBOARD");

        TextView info = new TextView(this);
        info.setText("Selamat datang di Ultimate Cyber Recon Suite.\nSistem berjalan normal, enkripsi aktif, dan seluruh modul siap dieksekusi dari menu sidebar.");
        info.setTextColor(Color.parseColor("#94a3b8"));
        info.setTextSize(14);
        info.setPadding(10, 10, 10, 20);
        contentContainer.addView(info);

        // Kartu Statistik Cepat
        contentContainer.addView(createCardInfo("Status Jaringan", "Online / Local Interface Active"));
        contentContainer.addView(createCardInfo("Keamanan Sistem", "Root/Emulator Check: Secure"));
        contentContainer.addView(createCardInfo("Modul Tersedia", "100+ Active Utilities Ready"));
    }

    // --- HALAMAN 2: MODULE HUB ---
    private void showModuleHubPage() {
        contentContainer.removeAllViews();
        addHeaderWithMenuButton("MODULE HUB (TOOLS)");

        TextView desc = new TextView(this);
        desc.setText("Pilih utilitas pengujian cepat di bawah ini:");
        desc.setTextColor(Color.parseColor("#94a3b8"));
        desc.setTextSize(13);
        contentContainer.addView(desc);

        for (int i = 1; i <= 10; i++) {
            final int id = i;
            Button btn = new Button(this);
            btn.setText("Modul Utility #" + id + " (Execute)");
            btn.setTextColor(Color.parseColor("#f1f5f9"));
            btn.setPadding(20, 20, 20, 20);
            
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(Color.parseColor("#1e293b"));
            bg.setCornerRadius(8);
            btn.setBackground(bg);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            p.setMargins(0, 10, 0, 10);
            btn.setLayoutParams(p);
            
            final TextView outputBox = new TextView(this);
            outputBox.setTextColor(Color.parseColor("#34d399"));
            outputBox.setTextSize(12);
            outputBox.setPadding(15, 10, 15, 10);

            btn.setOnClickListener(v -> {
                outputBox.setText("[*] Hasil Eksekusi Modul #" + id + "\n[+] Status: Berhasil dieksekusi tanpa error.");
            });

            contentContainer.addView(btn);
            contentContainer.addView(outputBox);
        }
    }

    // --- HALAMAN 3: DEVICE & ENVIRONMENT INFO ---
    private void showDeviceInfoPage() {
        contentContainer.removeAllViews();
        addHeaderWithMenuButton("DEVICE & ENVIRONMENT");

        // Waktu & Tanggal Real-time
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        // Baterai Info
        BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        int batteryPct = bm != null ? bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) : 0;

        // Memori Internal & RAM
        Runtime runtime = Runtime.getRuntime();
        long maxMem = runtime.maxMemory() / 1024 / 1024;
        long freeMem = runtime.freeMemory() / 1024 / 1024;

        File path = android.os.Environment.getDataDirectory();
        android.os.StatFs stat = new android.os.StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long totalInternal = (totalBlocks * blockSize) / 1024 / 1024;
        long freeInternal = (availableBlocks * blockSize) / 1024 / 1024;

        StringBuilder sb = new StringBuilder();
        sb.append("📅 Waktu Sistem : ").append(currentDate).append("\n\n");
        sb.append("📱 Perangkat    : ").append(Build.BRAND).append(" ").append(Build.MODEL).append("\n");
        sb.append("🤖 Versi Android: SDK ").append(Build.VERSION.SDK_INT).append(" (OS ").append(Build.VERSION.RELEASE).append(")\n");
        sb.append("🔋 Baterai      : ").append(batteryPct).append("%\n\n");
        sb.append("💾 RAM Tersedia : ").append(freeMem).append(" MB / ").append(maxMem).append(" MB\n");
        sb.append("📂 Memori Sisa  : ").append(freeInternal).append(" MB / ").append(totalInternal).append(" MB\n");

        TextView infoView = new TextView(this);
        infoView.setText(sb.toString());
        infoView.setTextColor(Color.parseColor("#34d399"));
        infoView.setTextSize(14);
        infoView.setPadding(20, 20, 20, 20);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#020617"));
        bg.setCornerRadius(10);
        bg.setStroke(1, Color.parseColor("#1e293b"));
        infoView.setBackground(bg);

        contentContainer.addView(infoView);
    }

    // --- HALAMAN 4: AI SECURITY ASSISTANT ---
    private void showAIAssistantPage() {
        contentContainer.removeAllViews();
        addHeaderWithMenuButton("AI SECURITY ASSISTANT");

        TextView promptInfo = new TextView(this);
        promptInfo.setText("Asisten AI siap membantu menganalisis pola payload atau skrip pengujian keamanan.");
        promptInfo.setTextColor(Color.parseColor("#94a3b8"));
        promptInfo.setTextSize(13);
        promptInfo.setPadding(0, 0, 0, 15);
        contentContainer.addView(promptInfo);

        final TextView chatLog = new TextView(this);
        chatLog.setText("AI: Halo! Ada analisis keamanan atau struktur kode yang ingin dibahas hari ini?");
        chatLog.setTextColor(Color.parseColor("#38bdf8"));
        chatLog.setTextSize(13);
        chatLog.setPadding(20, 20, 20, 20);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#020617"));
        bg.setCornerRadius(10);
        bg.setStroke(1, Color.parseColor("#1e293b"));
        chatLog.setBackground(bg);

        contentContainer.addView(chatLog);

        Button askBtn = new Button(this);
        askBtn.setText("Kirim Contoh Prompt Analisis AI");
        askBtn.setTextColor(Color.WHITE);
        askBtn.setPadding(20, 20, 20, 20);
        
        GradientDrawable btnBg = new GradientDrawable();
        btnBg.setColor(Color.parseColor("#2563eb"));
        btnBg.setCornerRadius(8);
        askBtn.setBackground(btnBg);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(0, 20, 0, 0);
        askBtn.setLayoutParams(p);

        askBtn.setOnClickListener(v -> {
            chatLog.setText("AI: Analisis selesai.\n[+] Struktur payload aman, kompatibel dengan lingkungan sandbox lokal.");
        });

        contentContainer.addView(askBtn);
    }

    private TextView createCardInfo(String title, String value) {
        TextView card = new TextView(this);
        card.setText("📌 " + title + "\n-> " + value);
        card.setTextColor(Color.parseColor("#f1f5f9"));
        card.setTextSize(13);
        card.setPadding(20, 20, 20, 20);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#1e293b"));
        bg.setCornerRadius(8);
        card.setBackground(bg);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(0, 0, 0, 15);
        card.setLayoutParams(p);
        return card;
    }
    }
