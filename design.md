# Kiến trúc RESTful API - Quản lý Task và User

Tài liệu này mô tả chi tiết thiết kế các Endpoint cho hệ thống quản lý Công việc (**Task**) và Người dùng (**User**). Hệ thống áp dụng mối quan hệ một-nhiều (1:N): Một **User** có thể có nhiều **Task**, và mỗi **Task** thuộc về một **User**.

---

## 1. Nguyên tắc thiết kế chung

* **Danh từ số nhiều:** Sử dụng `/users` và `/tasks` làm gốc (Resource Path).
* **HTTP Methods:** * `GET`: Đọc dữ liệu (Lấy danh sách hoặc chi tiết).
    * `POST`: Tạo mới dữ liệu (Sử dụng `@PostMapping` và `@RequestBody`).
    * `PUT`/`PATCH`: Cập nhật dữ liệu (`PUT` thay thế toàn bộ, `PATCH` cập nhật một phần).
    * `DELETE`: Xóa dữ liệu.
* **Kiểm tra dữ liệu (Validation):** Sử dụng các Annotation như `@Valid`, `@NotBlank`, `@NotNull`, `@Size` trên DTO để bắt lỗi JSON đầu vào trước khi xử lý.

---

## 2. Chi tiết hệ thống Endpoints

### 2.1. Quản lý Người dùng (Users)

#### Lấy toàn bộ danh sách người dùng
* **Endpoint:** `GET /api/v1/users`
* **Mô tả:** Trả về danh sách tất cả người dùng trong hệ thống.
* **Mã phản hồi:** `200 OK`

#### Tạo mới người dùng
* **Endpoint:** `POST /api/v1/users`
* **Mô tả:** Nhận dữ liệu JSON để tạo tài khoản mới.
* **Request Body (`@RequestBody`):**
    ```json
    {
      "username": "nguyenvana",
      "email": "ana@example.com",
      "role": "USER"
    }
    ```
* **Mã phản hồi:** `201 Created`

#### Cập nhật vai trò (Role) của người dùng
* **Endpoint:** `PATCH /api/v1/users/{userId}/role`
* **Mô tả:** Cập nhật một phần dữ liệu (chỉ thay đổi thuộc tính `role`).
* **Request Body (`@RequestBody`):**
    ```json
    {
      "role": "ADMIN"
    }
    ```
* **Mã phản hồi:** `200 OK`

#### Xóa một người dùng khỏi hệ thống
* **Endpoint:** `DELETE /api/v1/users/{userId}`
* **Mô tả:** Xóa người dùng theo ID tương ứng.
* **Mã phản hồi:** `204 No Content` hoặc `200 OK`

---

### 2.2. Quản lý Công việc (Tasks)

#### Lấy toàn bộ danh sách công việc
* **Endpoint:** `GET /api/v1/tasks`
* **Mô tả:** Trả về tất cả công việc hiện có trong hệ thống.
* **Mã phản hồi:** `200 OK`

#### Tạo mới công việc
* **Endpoint:** `POST /api/v1/tasks`
* **Mô tả:** Nhận JSON để tạo công việc mới.
* **Request Body (`@RequestBody`):**
    ```json
    {
      "title": "Soạn tài liệu API",
      "description": "Viết file design.md cho hệ thống",
      "priority": "high",
      "status": "PENDING"
    }
    ```
* **Mã phản hồi:** `201 Created`

#### Cập nhật trạng thái một công việc
* **Endpoint:** `PATCH /api/v1/tasks/{taskId}/status`
* **Mô tả:** Cập nhật thuộc tính `status` của công việc (Ví dụ: từ `PENDING` sang `COMPLETED`).
* **Request Body (`@RequestBody`):**
    ```json
    {
      "status": "COMPLETED"
    }
    ```
* **Mã phản hồi:** `200 OK`

#### Xóa một công việc
* **Endpoint:** `DELETE /api/v1/tasks/{taskId}`
* **Mô tả:** Xóa công việc dựa theo ID.
* **Mã phản hồi:** `204 No Content` hoặc `200 OK`

---

### 2.3. Tìm kiếm, Lọc và Liên kết dữ liệu (Query & Relationship)

#### Tìm các công việc có mức độ ưu tiên là "high"
* **Endpoint:** `GET /api/v1/tasks?priority=high`
* **Mô tả:** Sử dụng Query Parameter `priority` để lọc danh sách công việc.
* **Mã phản hồi:** `200 OK`

#### Tìm các công việc có độ ưu tiên là "high" và được giao cho người dùng với id là 1
* **Endpoint:** `GET /api/v1/tasks?priority=high&userId=1`
* **Mô tả:** Kết hợp nhiều tiêu chí lọc (Query Parameters) bằng ký tự `&`.
* **Mã phản hồi:** `200 OK`

#### Liệt kê toàn bộ công việc của 1 người dùng
* **Endpoint:** `GET /api/v1/users/{userId}/tasks`
* **Mô tả:** Sử dụng cấu trúc tài nguyên phân cấp (Sub-resource) để lấy danh sách công việc thuộc sở hữu của một User cụ thể.
* **Mã phản hồi:** `200 OK`

#### Gắn công việc cho người dùng (Foreign Key logic)
* **Endpoint:** `PUT /api/v1/users/{userId}/tasks/{taskId}`
* **Mô tả:** Thiết lập hoặc cập nhật mối quan hệ giữa User và Task. Hành động này sẽ cập nhật trường liên kết khóa ngoại (`user_id`) của bản ghi Task thành ID của User được chỉ định.
* **Mã phản hồi:** `200 OK`