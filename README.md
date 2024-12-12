# README.md

## Tổng quan

Đây là ứng dụng **BE FOREIGNER** được xây dựng bằng React Native và Expo ở frontend, và Spring Boot ở backend. Ứng dụng này cung cấp các chức năng :
    
1.  ***Quản lí tài khoản***

2.  ***Học từ vựng.***

3.  ***Xem từ vựng.***

4.  ***Quản lí gói đăng kí.***

5.  ***Đăng kí thông báo.***

### Cấu trúc dự án

Dự án được chia thành 2 thư mục chính:

  * **Frontend:** Chứa mã nguồn React Native sử dụng Expo.
  * **Backend:** Chứa mã nguồn Spring Boot.

## Cài đặt và chạy

### Điều kiện tiên quyết

  * **Node.js và npm (hoặc yarn):** Đảm bảo đã cài đặt các công cụ này trên máy của bạn.
  * **Java Development Kit (JDK):** Cần thiết để chạy ứng dụng Spring Boot.
  * **Một trình soạn thảo code:** Ví dụ như Visual Studio Code, Sublime Text.

### Cài đặt

1.  **Clone repository:**
    ```bash
    git clone https://github.com/duyhelloword/be-foreigner.git
    ```
2.  **Cài đặt dependencies:**
      * **Frontend:**
        ```bash
        cd Frontend
        npm install
        ```
      * **Backend:**
        ```bash
        cd Backend
        mvn clean install
        ```

### Cấu hình

  * **Backend:**
      * **Tạo file secret.properties:** Tạo một file có tên `secret.properties` trong thư mục `Backend/src/main/resources` và điền các thông tin sau:
        ```properties
        cloudinary.url=https://api.cloudinary.com/v1_1/[your_cloud_name]/image/upload
        mail.username=your_email@example.com
        mail.password=your_password
        ```
        **Lưu ý:** Thay thế các giá trị trong dấu ngoặc vuông bằng thông tin của tài khoản Cloudinary và email của bạn.

### Chạy ứng dụng

  * **Backend:**
    ```bash
    cd Backend
    mvn spring-boot:run
    ```
  * **Frontend:**
    ```bash
    cd Frontend
    npm start
    ```
    Sau đó, sử dụng ứng dụng Expo Go trên điện thoại hoặc máy tính bảng để xem ứng dụng.

## Cách sử dụng

[Mô tả chi tiết cách sử dụng các tính năng chính của ứng dụng]

## Công nghệ sử dụng

  * **Frontend:** React Native, Expo
  * **Backend:** Spring Boot, MySQL, ...

## Tài liệu tham khảo

  * **React Native:** [https://reactnative.dev/](https://www.google.com/url?sa=E&source=gmail&q=https://reactnative.dev/)
  * **Expo:** [https://expo.io/](https://www.google.com/url?sa=E&source=gmail&q=https://expo.io/)
  * **Spring Boot:** [https://spring.io/guides/gs/spring-boot/](https://www.google.com/url?sa=E&source=gmail&q=https://spring.io/guides/gs/spring-boot/)

## Góp ý

Nếu bạn muốn đóng góp cho dự án, vui lòng tạo một pull request.