[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NxoIfoj_)
# Laporan Praktikum Modul 3: GUI
**Nama:** `[Nama Lengkap Anda]`
**NIM:** `[NIM Anda]`

---

## 🎯 **Tujuan Praktikum**

Pada modul ini, tujuan utama adalah memahami dan mengimplementasikan konsep Graphical User Interface (GUI) menggunakan Java, dengan memanfaatkan Swing dan AWT sebagai dasar pengembangan antarmuka. Fokus praktikum mencakup:

1. Perancangan tampilan antarmuka menggunakan komponen GUI dasar.
2. Pengaturan tata letak (layout) agar tampilan aplikasi menjadi terstruktur dan mudah digunakan.
3. Penerapan event handling untuk menghubungkan aksi pengguna dengan logika program.
4. Interaksi dinamis dengan pengguna melalui dialog atau pesan yang ditampilkan aplikasi.

---

## 📖 Konsep dan Pelajaran Utama yang Diimplementasikan (Semakin banyak poin konsep yang dijelaskan semakin bagus)

### 1. **Inheritance (Pewarisan)**
Saya menerapkan konsep inheritance untuk menciptakan hierarki kelas yang logis dan efisien.
- **Superclass:** `[Nama Superclass Anda, misal: 'Karakter']`
- **Subclass:** `[Nama Subclass, misal: 'Pahlawan' dan 'Musuh']`
Subclass `[Nama Subclass]` mewarisi atribut umum seperti `[contoh atribut, misal: 'nama' dan 'healthPoint']` dari superclass, sehingga menghindari duplikasi kode.

### 2. **Encapsulation (Enkapsulasi)**
Untuk menjaga integritas data, saya menerapkan enkapsulasi pada semua kelas.
- Semua atribut dideklarasikan dengan *access modifier* `private`.
- Akses ke atribut-atribut tersebut dikontrol melalui metode *public* `getter` dan `setter`. Hal ini memastikan bahwa data tidak dapat diubah secara sembarangan dari luar kelas.

### 3. **Polymorphism (Polimorfisme)**
Polimorfisme diimplementasikan melalui **method overriding**.
- Metode `[Nama Metode, misal: 'serang()']` yang ada di superclass, saya definisikan ulang di setiap subclass dengan perilaku yang berbeda.
- `Pahlawan` mungkin memiliki implementasi serangan dengan bonus damage, sedangkan `Musuh` memiliki serangan standar. Ini menunjukkan bagaimana satu nama metode dapat memiliki banyak bentuk perilaku.

### 4. Dan seterusnya......
blablablablablablablablablablablablablab
- balbalbalbalbalbalablablablablablablablablablab
- blablabalbalbalbalbalbalbalbalbalbalbalbalbalablabla
---

## 💻 Implementasi Kode dan Hasil

Berikut adalah cuplikan kode kunci yang menunjukkan penerapan konsep-konsep di atas:

**Cuplikan Kode:**
```java
// [Sertakan cuplikan kode paling relevan di sini]
// Contoh yang baik adalah bagian main() yang menunjukkan polimorfisme
Karakter pahlawan = new Pahlawan("Arjuna");
Karakter musuh = new Musuh("Rahwana");

pahlawan.serang(); // Output: Arjuna menyerang dengan panah sakti!
musuh.serang();   // Output: Rahwana melakukan serangan biasa!
```

**Hasil Screenshot:**
*(Sertakan screenshot output dari program Anda di sini)*
1. Screenshot 1
2. Screenshot 2
3. Screenshot 3

---

## ✔️ Kesimpulan
buat kesimpulan dari hasil pratikum dan penjelasan yang dibuat
