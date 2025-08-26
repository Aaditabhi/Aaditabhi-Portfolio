import React, { useState } from 'react';
import './App.css';
import InitialButton from '../Components/InitialButton.jsx';
import PathButton from '../Components/PathButton.jsx';
import { useNavigate } from 'react-router-dom';
export const colors = ["#fa8900", "#0c29ff", "#eae000", "#ff0000", "#008c00"];

const Level1 = () => {
  
  const navigate = useNavigate();
  const NavigateBackToStartScreen = () => {
    navigate('/');
  }
  const NavigateToNextLevel = () => {
    navigate('/level2');
  }
  const [activeColor, setActiveColor] = useState("#FFFFFF");
  const [lastClicked, setLastClicked] = useState([null, null]);
  const [colorLastClicked, setColorLastClicked] = useState([
                                                           [-1, -1],
                                                           [-1, -1],
                                                           [-1, -1],
                                                           [-1, -1],
                                                           [-1, -1]
                                                                      ]);
  const [isComplete, setIsComplete] = useState([false,false,false,false,false])

  /*<------------------------------------------------>*/
  const rows = 5;
  const cols = 5;

  const grid = [];
  
  for (let row = 0; row < rows; row++) {
    const rowItems = [];
    for (let col = 0; col < cols; col++) {
        if(col == 0 || col == (cols-1)){
            rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[row]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[row]} setIsComplete={(value) => {
                                const newIsComplete = [...isComplete];
                                newIsComplete[row] = value;
                                setIsComplete(newIsComplete);
                            }}/>); 
        } else {
          rowItems.push(<PathButton key={`${row}-${col}`} row={row} col={col} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} />);
        }
      
      
    }
    grid.push(
      <div key={row} style={{ display: 'flex', justifyContent: 'center' }}>
        {rowItems}
      </div>
    );
  }
  function allTrue() {
      return isComplete.every(Boolean);
  }

  return (
    <div style={{ padding: 20,textAlign: 'center' }}>
      <button onClick={NavigateBackToStartScreen} style = {{marginRight: 20}}>Home</button>
      <button onClick = {NavigateToNextLevel}>Next</button>
      <h1>Tutorial</h1>
      {grid}
      {allTrue() && <h1>You Win!</h1>}
    </div>
  );
};

export default Level1;
