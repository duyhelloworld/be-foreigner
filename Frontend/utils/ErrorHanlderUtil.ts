import { AxiosError } from "axios";
import { ApiErrorResponse } from "../types/apimodels";

export const handleError = (error: any): ApiErrorResponse => {
  if (error instanceof AxiosError) {
    const data = error.response?.data;
    return {
      errorCode: data.errorCode,
      messages: data.messages,
    };
  } else {
    console.error("ErrorHanlder : unknown error ", error);
    return {
      errorCode: 9999,
      messages: ["lỗi bất định"],
    };
  }
};
