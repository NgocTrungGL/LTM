-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 22, 2025 lúc 06:40 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `furniture_store`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Ghế'),
(2, 'Sofa'),
(3, 'Giường'),
(4, 'Tủ'),
(5, 'Bàn'),
(6, 'Đèn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT current_timestamp(),
  `total_price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT 0,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `image_url`, `stock`, `category_id`) VALUES
(1, 'Ghế Gỗ Sồi', 'Ghế làm từ gỗ sồi cao cấp.', 750000.00, '/images/ghe1.jpg', 20, 1),
(2, 'Ghế Đệm Mềm', 'Thiết kế hiện đại, đệm êm ái.', 850000.00, '/images/ghe2.jpg', 15, 1),
(3, 'Ghế Văn Phòng', 'Ghế xoay văn phòng tiện lợi.', 950000.00, '/images/ghe3.jpg', 10, 1),
(4, 'Ghế Cafe Vintage', 'Phong cách cổ điển, phù hợp quán cafe.', 670000.00, '/images/ghe4.jpg', 18, 1),
(5, 'Ghế Ăn Nhập Khẩu', 'Ghế nhập khẩu, thiết kế sang trọng.', 1200000.00, '/images/ghe5.jpg', 8, 1),
(6, 'Sofa Da Cao Cấp', 'Chất liệu da mềm mại, cao cấp.', 12500000.00, '/images/sofa1.jpg', 5, 2),
(7, 'Sofa Góc L', 'Tiện lợi cho phòng khách rộng.', 8500000.00, '/images/sofa2.jpg', 6, 2),
(8, 'Sofa Băng 3 Chỗ', 'Dành cho phòng khách nhỏ.', 6800000.00, '/images/sofa3.jpg', 9, 2),
(9, 'Sofa Vải Nhung', 'Chất liệu nhung êm ái, sang trọng.', 9200000.00, '/images/sofa4.jpg', 4, 2),
(10, 'Sofa Đa Năng', 'Có thể chuyển thành giường.', 10200000.00, '/images/sofa5.jpg', 7, 2),
(11, 'Giường Gỗ Tự Nhiên', 'Giường làm từ gỗ xoan đào.', 7500000.00, '/images/giuong1.jpg', 12, 3),
(12, 'Giường Tầng Trẻ Em', 'Tiết kiệm không gian.', 6400000.00, '/images/giuong2.jpg', 10, 3),
(13, 'Giường Hộp', 'Có ngăn kéo đựng đồ.', 8300000.00, '/images/giuong3.jpg', 7, 3),
(14, 'Giường Đệm Lò Xo', 'Kèm đệm lò xo cao cấp.', 11000000.00, '/images/giuong4.jpg', 6, 3),
(15, 'Giường Da Hiện Đại', 'Khung da nhân tạo thời thượng.', 12500000.00, '/images/giuong5.jpg', 5, 3),
(16, 'Tủ Quần Áo 2 Cánh', 'Chất liệu gỗ MDF chống ẩm.', 4800000.00, '/images/tu1.jpg', 11, 4),
(17, 'Tủ Gỗ Tự Nhiên', 'Tủ đựng quần áo bằng gỗ tự nhiên.', 6200000.00, '/images/tu2.jpg', 8, 4),
(18, 'Tủ Kệ Trang Trí', 'Dùng để trang trí phòng khách.', 3200000.00, '/images/tu3.jpg', 14, 4),
(19, 'Tủ Bếp Treo Tường', 'Tủ bếp hiện đại, dễ lắp đặt.', 5600000.00, '/images/tu4.jpg', 7, 4),
(20, 'Tủ Đựng Giày', 'Tủ giày nhỏ gọn, tiện dụng.', 2700000.00, '/images/tu5.jpg', 12, 4),
(21, 'Bàn Làm Việc Gỗ', 'Thiết kế hiện đại, đơn giản.', 3500000.00, '/images/ban1.jpg', 10, 5),
(22, 'Bàn Cafe Nhỏ', 'Phù hợp quán cafe, góc chill.', 2100000.00, '/images/ban2.jpg', 13, 5),
(23, 'Bàn Học Sinh', 'Bàn học cho học sinh tiểu học.', 2600000.00, '/images/ban3.jpg', 15, 5),
(24, 'Bàn Trà Phòng Khách', 'Bàn trà gỗ tròn nhỏ.', 2900000.00, '/images/ban4.jpg', 11, 5),
(25, 'Bàn Gấp Đa Năng', 'Có thể gấp gọn sau khi sử dụng.', 1900000.00, '/images/ban5.jpg', 14, 5),
(26, 'Đèn Trang Trí Trần Nhà', 'Đèn LED chiếu sáng trần.', 1400000.00, '/images/den1.jpg', 20, 6),
(27, 'Đèn Ngủ Để Bàn', 'Ánh sáng dịu nhẹ, tốt cho mắt.', 890000.00, '/images/den2.jpg', 25, 6),
(28, 'Đèn Sàn Đọc Sách', 'Có thể điều chỉnh độ sáng.', 1650000.00, '/images/den3.jpg', 12, 6),
(29, 'Đèn Chùm Sang Trọng', 'Thiết kế đèn chùm cổ điển.', 3500000.00, '/images/den4.jpg', 4, 6),
(30, 'Đèn Tường Trang Trí', 'Đèn treo tường phong cách.', 1100000.00, '/images/den5.jpg', 17, 6),
(31, 'Đèn Trang Trí Trần Nhà', 'Đèn LED chiếu sáng trần.', 1400000.00, '/images/den1.jpg', 20, 6),
(32, 'Đèn Ngủ Để Bàn', 'Ánh sáng dịu nhẹ, tốt cho mắt.', 890000.00, '/images/den2.jpg', 25, 6),
(33, 'Đèn Sàn Đọc Sách', 'Có thể điều chỉnh độ sáng.', 1650000.00, '/images/den3.jpg', 12, 6),
(34, 'Đèn Chùm Sang Trọng', 'Thiết kế đèn chùm cổ điển.', 3500000.00, '/images/den4.jpg', 4, 6),
(35, 'Đèn Tường Trang Trí', 'Đèn treo tường phong cách.', 1100000.00, '/images/den5.jpg', 17, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `email`) VALUES
(1, 'zunzun', '123456', 'Zun Zun', 'trung@gmail.com');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
