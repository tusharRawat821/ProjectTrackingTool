import './App.css';
import DashBoard from './components/DashBoard';
import Header from './components/Layout/Header';


function App() {
  return (
    <div className="App">
      <Header />
      <DashBoard className="Dashboard"/>
    </div>
  );
}

export default App;
