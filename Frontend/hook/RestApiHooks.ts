import { useState } from "react";
import axios, { AxiosResponse } from "axios";

const ROOT_URL = "http://192.168.196.144";

interface ApiErrorResponse {
  errorCode: number;
  messages: string[];
}

interface ApiResponse<T> {
  data: T | null; 
  loading: boolean;
  error: ApiErrorResponse | null; 
  fetchData: (params?: Record<string, any>) => Promise<void>;
}

const useApi = <T = any>(url: string): ApiResponse<T> => {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<ApiErrorResponse | null>(null); // Thay đổi kiểu cho state error

  const fetchData = async (params?: Record<string, any>): Promise<void> => {
    setLoading(true);
    setError(null);
    try {
      const response: AxiosResponse<T> = await axios.get(`${ROOT_URL}/${url}`, { params });
      setData(response.data);
    } catch (err) {
      if (axios.isAxiosError(err) && err.response) {
        // Nếu có response từ server
        setError({
          errorCode: err.response.status, // Mã lỗi HTTP
          messages: err.response.data.messages || ["An error occurred"], // Lấy messages từ response hoặc thông báo mặc định
        });
      } else {
        setError({ errorCode: 0, messages: ["An unexpected error occurred"] }); // Xử lý lỗi không xác định
      }
    } finally {
      setLoading(false);
    }
  };

  return { data, loading, error, fetchData };
};

export default useApi;
