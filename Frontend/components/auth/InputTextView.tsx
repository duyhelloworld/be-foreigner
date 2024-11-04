import { StyleSheet, Text, TextInput, View, Pressable } from "react-native";
import React, { useState } from "react";
import { AppColors } from "../../types/Colors";
import { Ionicons } from "@expo/vector-icons";

interface InputTextViewProp {
  text: string;
  value: string;
  setValue: (v: string) => void;
  error?: string;
  secure?: boolean;
}

const InputTextView = ({
  text,
  value,
  setValue,
  error,
  secure = false,
}: InputTextViewProp) => {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <View style={styles.inputContainer}>
      <View style={styles.inputWrapper}>
        <TextInput
          placeholder={text}
          placeholderTextColor={AppColors.gray}
          style={[styles.input, error ? styles.inputError : null]}
          value={value}
          onChangeText={setValue}
          secureTextEntry={secure && !showPassword}
        />
        {secure && (
          <Pressable
            onPress={() => setShowPassword(!showPassword)}
            style={styles.showButton}
          >
            <Ionicons name={showPassword ? "eye-off-outline" : "eye-outline"} size={24} color={AppColors.grayDark} />
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
    backgroundColor: AppColors.lightGray,
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