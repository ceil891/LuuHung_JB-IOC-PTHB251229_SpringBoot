package SS5.course.dto.response;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    // Constructor không tham số (Bắt buộc để Jackson thư viện map JSON)
    public ApiResponse() {
    }

    // Constructor đầy đủ tham số khớp với tầng Controller đang gọi
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters và Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}