import React, { useState } from 'react';
import './App.css';
import InitialButton from '../Components/InitialButton.jsx';
import PathButton from '../Components/PathButton.jsx';
import { useNavigate } from 'react-router-dom';
export const colors = ["#fa8900", "#0c29ff", "#eae000", "#ff0000", "#008c00"];

const Level2 = () => {

  const navigate = useNavigate();
  const NavigateBackToStartScreen = () => {
    navigate('/');
  }
  const NavigateToNextLevel = () => {
    navigate('/level3');
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
        if((col == 0 && row == 0)|| (col == 1 && row == 4)){
            rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[0]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[0]} setIsComplete={(value) => {
                                const newIsComplete = [...isComplete];
                                newIsComplete[0] = value;
                                setIsComplete(newIsComplete);
                            }}/>); 
        }
        else if ((col == 2 && row == 0) || (col == 1 && row == 3)){
          rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[1]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[1]} setIsComplete={(value) => {
              const newIsComplete = [...isComplete];
              newIsComplete[1] = value;
              setIsComplete(newIsComplete);
          }}/>); 
        }
        else if ((col == 2 && row == 1) || (col == 2 && row == 4)){
          rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[2]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[2]} setIsComplete={(value) => {
              const newIsComplete = [...isComplete];
              newIsComplete[2] = value;
              setIsComplete(newIsComplete);
          }}/>); 
        }
        else if ((col == 4 && row == 0) || (col == 3 && row == 3)){
          rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[3]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[3]} setIsComplete={(value) => {
              const newIsComplete = [...isComplete];
              newIsComplete[3] = value;
              setIsComplete(newIsComplete);
          }}/>); 
        }
        else if ((col == 4 && row == 1) || (col == 3 && row == 4)){
          rowItems.push(<InitialButton key={`${row}-${col}`} row={row} col={col} color ={colors[4]} setActiveColor = {setActiveColor} activeColor = {activeColor} lastClicked = {lastClicked} setLastClicked = {setLastClicked} colorLastClicked = {colorLastClicked} setColorLastClicked = {setColorLastClicked} isComplete = {isComplete[4]} setIsComplete={(value) => {
              const newIsComplete = [...isComplete];
              newIsComplete[4] = value;
              setIsComplete(newIsComplete);
          }}/>); 
        }
        else {
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
    <div style={{ padding: 20, textAlign: 'center' }}>
      <button onClick={NavigateBackToStartScreen} style = {{marginRight: 20}}>Home</button>
      <button onClick={NavigateToNextLevel}>Next</button>
      <h1>Level 2</h1>
      {grid}
      {allTrue() && <h1>You Win!</h1>}
    </div>
  );
};

export default Level2;
