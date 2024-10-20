package kau.capstone2.common.dto;

import lombok.*;

@Getter
@Builder
public class ApiResponseDTO<T> {

    @NonNull
    private final boolean success;
    private final T data;
    private final String message;

    public static <T> ApiResponseDTO<T> success(T data) {
        return ApiResponseDTO.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T> ApiResponseDTO<T> fail(String message) {
        return ApiResponseDTO.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}
