
import React from 'react';
import { useNavigate } from 'react-router-dom';

const StartScreen = () => {
  const navigate = useNavigate();

  const StartLevel1 = () => {
    navigate('/level1');
  }
  const StartLevel2 = () => {
    navigate('/level2')
  }
  const StartLevel3 = () => {
    navigate('/level3')
  }
  const StartLevel4 = () => {
    navigate('/level4')
  }
  const StartLevel5 = () => {
    navigate('/level5')
  }
  const StartLevel6 = () => {
    navigate('/level6')
  }
  /*const StartLevel2 = () =>
    navigate('/level2');
  }*/
  

  return (
    <>
    <div>
      <h1>Flow!</h1>
      <button 
        onClick={StartLevel1}
        style = {{
          margin: '10px',
        }}
        >
        Tutorial
      </button>
      <button
        onClick={StartLevel2}
        style = {{
          margin: '10px',
        }}
        >Level 2
      </button>
      <button
        onClick={StartLevel3}
        style = {{
          margin: '10px',
        }}
        >Level 3
      </button>
      <button
        onClick={StartLevel4}
        style = {{
          margin: '10px',
        }}
        >Level 4
      </button>
      <button
        onClick={StartLevel5}
        style = {{
          margin: '10px',
        }}
        >Level 5
      </button>
    </div>
    <div style = {{paddingTop:20}}>
      <button
        onClick={StartLevel6}
        style = {{
          margin: '10px',
        }}
        >Level 6
      </button>
    </div>
    </>
  );
};

export default StartScreen;