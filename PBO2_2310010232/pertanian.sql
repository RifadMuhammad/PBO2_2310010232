-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Nov 2025 pada 06.33
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pertanian`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kurir`
--

CREATE TABLE `kurir` (
  `id_kurir_int` int(11) NOT NULL,
  `nama_kurir` varchar(100) NOT NULL,
  `kode_kurir` varchar(50) DEFAULT NULL,
  `ongkos_kirim` decimal(15,2) DEFAULT NULL,
  `tambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hapus` timestamp NULL DEFAULT NULL,
  `tampil` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kurir`
--

INSERT INTO `kurir` (`id_kurir_int`, `nama_kurir`, `kode_kurir`, `ongkos_kirim`, `tambah`, `edit`, `hapus`, `tampil`) VALUES
(8, 'JNE Express', 'JNE', 21000.00, '2025-10-27 16:16:10', '2025-11-04 04:58:32', NULL, 1),
(9, 'SiCepat', 'SCP', 18000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1),
(10, 'J&T Express', 'JNT', 21000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1),
(11, 'Ninja Xpress', 'NINJA', 19000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `id_int` int(11) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga` decimal(15,2) NOT NULL,
  `berat` varchar(20) DEFAULT NULL,
  `stok` varchar(50) DEFAULT NULL,
  `gambar_produk` varchar(255) DEFAULT NULL,
  `tambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hapus` timestamp NULL DEFAULT NULL,
  `tampil` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`id_int`, `nama_produk`, `deskripsi`, `kategori`, `harga`, `berat`, `stok`, `gambar_produk`, `tambah`, `edit`, `hapus`, `tampil`) VALUES
(3, 'Pupuk Urea', 'Pupuk non-subsidi', 'Pupuk', 150000.00, '5', '100', NULL, '2025-10-27 16:16:10', '2025-10-27 16:17:44', NULL, 1),
(4, 'Bibit Cabai Rawit', 'Isi 50 biji', 'Bibit', 25000.00, '50', '500', NULL, '2025-10-27 16:16:10', '2025-10-27 16:17:54', NULL, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `toko`
--

CREATE TABLE `toko` (
  `id_toko_int` int(11) NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `alamat_toko` varchar(255) DEFAULT NULL,
  `no_hp_toko` varchar(20) DEFAULT NULL,
  `rating` varchar(10) DEFAULT NULL,
  `produk` varchar(255) DEFAULT NULL,
  `tgl_daftar_user` date DEFAULT NULL,
  `tambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hapus` timestamp NULL DEFAULT NULL,
  `tampil` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `toko`
--

INSERT INTO `toko` (`id_toko_int`, `nama_toko`, `alamat_toko`, `no_hp_toko`, `rating`, `produk`, `tgl_daftar_user`, `tambah`, `edit`, `hapus`, `tampil`) VALUES
(2, 'Toko Tani Makmur', 'Jl. Sudirman 12, Jakarta', '081234567890', '4.5', 'Pupuk, Bibit', '2024-01-15', '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1),
(3, 'Sinar Tani', 'Jl. Gatot Subroto 50, Bandung', '081111111111', '4.8', 'Alat Pertanian', '2024-02-20', '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_int` int(11) NOT NULL,
  `tgl_beli` date DEFAULT NULL,
  `nama_user` varchar(100) DEFAULT NULL,
  `nama_toko` varchar(100) DEFAULT NULL,
  `nama_produk` varchar(100) DEFAULT NULL,
  `harga` decimal(15,2) DEFAULT NULL,
  `jlh_produk` int(11) DEFAULT NULL,
  `total_harga` decimal(15,2) DEFAULT NULL,
  `kode_kurir` varchar(50) DEFAULT NULL,
  `ongkos_kirim` decimal(15,2) DEFAULT NULL,
  `jlh_total` decimal(15,2) DEFAULT NULL,
  `tambah` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hapus` timestamp NULL DEFAULT NULL,
  `tampil` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_int`, `tgl_beli`, `nama_user`, `nama_toko`, `nama_produk`, `harga`, `jlh_produk`, `total_harga`, `kode_kurir`, `ongkos_kirim`, `jlh_total`, `tambah`, `edit`, `hapus`, `tampil`) VALUES
(2, '2025-10-01', 'Budi', 'Toko Tani Makmur', 'Pupuk Urea', 150000.00, 2, 300000.00, 'JNE', 20000.00, 320000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1),
(3, '2025-10-03', 'Citra', 'Sinar Tani', 'Cangkul', 85000.00, 1, 85000.00, 'SCP', 18000.00, 103000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1),
(4, '2025-10-05', 'Doni', 'Berkah Tani', 'Bibit Cabai Rawit', 25000.00, 5, 125000.00, 'JNT', 21000.00, 146000.00, '2025-10-27 16:16:10', '2025-10-27 16:16:10', NULL, 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kurir`
--
ALTER TABLE `kurir`
  ADD PRIMARY KEY (`id_kurir_int`),
  ADD UNIQUE KEY `kode_kurir` (`kode_kurir`),
  ADD KEY `idx_kurir_kode` (`kode_kurir`);

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_int`),
  ADD KEY `idx_produk_nama` (`nama_produk`);

--
-- Indeks untuk tabel `toko`
--
ALTER TABLE `toko`
  ADD PRIMARY KEY (`id_toko_int`),
  ADD KEY `idx_toko_nama` (`nama_toko`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_int`),
  ADD KEY `fk_kurir` (`kode_kurir`),
  ADD KEY `idx_transaksi_tgl` (`tgl_beli`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kurir`
--
ALTER TABLE `kurir`
  MODIFY `id_kurir_int` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `produk`
--
ALTER TABLE `produk`
  MODIFY `id_int` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `toko`
--
ALTER TABLE `toko`
  MODIFY `id_toko_int` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_int` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_kurir` FOREIGN KEY (`kode_kurir`) REFERENCES `kurir` (`kode_kurir`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
