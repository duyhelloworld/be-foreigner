import { StyleSheet, Text, TextInput, View, Pressable, TextInputProps } from "react-native";
import React, { useState } from "react";
import { AppColors } from "../../types/colors";
import { Ionicons } from "@expo/vector-icons";

interface InputTextViewProp {
  value?: string;
  setValue?: (v: string) => void;
  placeholder?: string;
  error?: string;
  secure?: boolean;
  inputType?: TextInputProps["keyboardType"],
  enable?: boolean;
  showPassword?: boolean; 
  toggleShowPassword?: () => void;
}

const InputTextView = ({
  placeholder,
  value,
  setValue,
  error,
  secure = false,
  enable = true,
  inputType = "default",
  showPassword = false,
  toggleShowPassword,
}: InputTextViewProp) => {

  return (
    <View style={styles.inputContainer}>
      <View style={styles.inputWrapper}>
        <TextInput
          placeholder={placeholder}
          placeholderTextColor={AppColors.gray}
          style={[styles.input, error ? styles.inputError : null]}
          value={value}
          editable={enable}
          keyboardType={inputType}
          onChangeText={v => setValue && setValue(v)}
          secureTextEntry={secure && !showPassword}
        />
        {secure && (
          <Pressable
            onPress={toggleShowPassword}
            style={styles.showButton}
          >
            <Ionicons
              name={showPassword ? "eye-off-outline" : "eye-outline"}
              size={24}
              color={AppColors.grayDark}
            />
          </Pressable>
        )}
      </View>
      {error ? <Text style={styles.errorText}>{error}</Text> : null}
    </View>
  );
};

export default InputTextView;

const styles = StyleSheet.create({
  inputContainer: {
    marginBottom: 15,
  },
  inputWrapper: {
    flexDirection: "row",
    alignItems: "center",
    borderColor: AppColors.light,
    borderWidth: 1,
    borderRadius: 8,
    backgroundColor: AppColors.placeholder,
    paddingHorizontal: 15,
  },
  input: {
    flex: 1,
    height: 55,
  },
  showButton: {
    paddingHorizontal: 10,
  },
  showButtonText: {
    color: AppColors.grayDark,
  },
  inputError: {
    borderColor: AppColors.red,
  },
  errorText: {
    color: AppColors.red,
    fontSize: 12,
    marginTop: 5,
  },
});