import React, { useRef, useState } from "react";

const PathButton = ({
  row,
  col,
  activeColor,
  colorLastClicked,
  setColorLastClicked,
}) => {
  const savedColor = useRef(activeColor);
  const [isClicked, setClicked] = useState(true);
  const [prevCell, setPrevCell] = useState([null, null]);

  const handleClick = () => {
    let colorIndex = getColorNumByHex(activeColor);

    if (
      (row == colorLastClicked[colorIndex][0] + 1 &&
        col == colorLastClicked[colorIndex][1]) ||
      (row == colorLastClicked[colorIndex][0] - 1 &&
        col == colorLastClicked[colorIndex][1]) ||
      (row == colorLastClicked[colorIndex][0] &&
        col == colorLastClicked[colorIndex][1] + 1) ||
      (row == colorLastClicked[colorIndex][0] &&
        col == colorLastClicked[colorIndex][1] - 1) ||
      (row == colorLastClicked[colorIndex][0] &&
        col == colorLastClicked[colorIndex][1])
    ) {
      //Active color is selected and path button is white
      if (activeColor != "#FFFFFF" && getBGColor() == "#FFFFFF") {
        setPrevCell([
          colorLastClicked[colorIndex][0],
          colorLastClicked[colorIndex][1],
        ]);
        setClicked(!isClicked);
        savedColor.current = activeColor;
        if (getBGColor() != "#FFFFFF") {
          updateColorCoords(colorIndex, row, col);
        }
      }
      //Path button is selected as active color, and they are both not whgite
      else if (
        getBGColor() == activeColor &&
        activeColor != "#FFFFFF" &&
        colorLastClicked[colorIndex][0] == row &&
        colorLastClicked[colorIndex][1] == col
      ) {
        setClicked(!isClicked);
        savedColor.current = "#FFFFFF";
        let a = prevCell[0];
        let b = prevCell[1];
        updateColorCoords(colorIndex, a, b);
      } 
    }
  };
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

  const getBGColor = () => {
    if (savedColor.current == null) {
      return "#FFFFFF";
    }
    return savedColor.current;
  };

  const style = {
    borderwidth: 10,
    backgroundColor: getBGColor(),
  };

  return (
    <button className="pathButton" onClick={handleClick} style={style}></button>
  );
};

export default PathButton;
