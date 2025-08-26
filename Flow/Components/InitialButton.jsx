import React, { useEffect, useState } from "react";

const InitialButton = ({
  row,
  col,
  color,
  setActiveColor,
  activeColor,
  colorLastClicked,
  setColorLastClicked,
  isComplete,
  setIsComplete,
}) => {
  const [isClicked, setClicked] = useState(false);

  const updateColorCoords = (index, newX, newY) => {
    setColorLastClicked((prev) => {
      const newCoords = [...prev];
      newCoords[index] = [newX, newY];
      return newCoords;
    });
  };
  const getColorNumByHex = (hex) => {
    switch (hex) {
      case "#fa8900":
        return 0;
      case "#0c29ff":
        return 1;
      case "#eae000":
        return 2;
      case "#ff0000":
        return 3;
      default:
        return 4;
    }
  };

  const handleClick = () => {
    setActiveColor(color);
    setClicked((prev) => !prev);
    let colorIndex = getColorNumByHex(color);
    if (
      colorLastClicked[colorIndex][0] == -1 &&
      colorLastClicked[colorIndex][1] == -1
    ) {
      updateColorCoords(colorIndex, row, col);
    } else {
      if (
        (row == colorLastClicked[colorIndex][0] + 1 &&
          col == colorLastClicked[colorIndex][1]) ||
        (row == colorLastClicked[colorIndex][0] - 1 &&
          col == colorLastClicked[colorIndex][1]) ||
        (row == colorLastClicked[colorIndex][0] &&
          col == colorLastClicked[colorIndex][1] + 1) ||
        (row == colorLastClicked[colorIndex][0] &&
          col == colorLastClicked[colorIndex][1] - 1)
      ) {
        setIsComplete(!isComplete);
      } else {
        setIsComplete(false);
      }
    }
  };
  const getBGColor = () => {
    if (isComplete) {
      return color;
    } else {
      return "white";
    }
  };
  useEffect(() => {
    if (isClicked) {
      setActiveColor(color);
    } else {
      setActiveColor("#FFFFFF");
    }
  }, [isClicked]);

  const style = {
    position: "relative",
    backgroundColor: getBGColor(),
    width: "60px",
    height: "60px",
    margin: "4px",
    fontWeight: "bold",
    fontSize: "16px",
    borderColor: "black",
    borderWidth: "2px",
  };
  const style2 = {
    position: "absolute",
    top: "50%",
    left: "50%",
    width: "40px",
    height: "40px",
    backgroundColor: color,
    borderRadius: "50%",
    transform: "translate(-50%, -50%)",
    borderColor: activeColor == color ? "black" : "transparent",
    borderWidth: "4px",
    borderStyle: "solid",
  };
  return (
    <button onClick={handleClick} style={style}>
      <div style={style2} />
    </button>
  );
};

export default InitialButton;
