import { Svg, Circle, Text as SvgText } from "react-native-svg";
import { AppColors } from "../../types/colors";

interface CircularProgressBarProp {
  progress: number;
}

const CircularProgressBar = ({ progress }: CircularProgressBarProp) => {
  const radius = 30;
  const strokeWidth = 5;
  const size = radius + strokeWidth / 2;
  const circumference = 2 * Math.PI * radius;

  return (
    <Svg width={radius * 2 + strokeWidth} height={radius * 2 + strokeWidth}>
      <Circle
        stroke={AppColors.grayDark}
        fill="none"
        cx={size}
        cy={size}
        r={radius}
        strokeWidth={strokeWidth}
      />
      <Circle
        stroke={AppColors.white}
        fill="none"
        cx={size}
        cy={size}
        r={radius}
        transform={`rotate(-90 ${size} ${size})`}
        strokeWidth={strokeWidth}
        strokeDasharray={circumference}
        strokeDashoffset={progress}
      />
      <SvgText
        x={size}
        y={size + strokeWidth}
        textAnchor="middle"
        fill="white"
        fontSize={10}
      >
        {`${progress}%`}
      </SvgText>
    </Svg>
  );
};

export default CircularProgressBar;