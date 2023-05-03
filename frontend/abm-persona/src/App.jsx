import React from "react";
import People from "./components/list/People";
import { Routes, Route } from "react-router-dom";
import Person from "./components/person/Person";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<People />} />
        <Route path="/person/:id" element={<Person />} />
      </Routes>
    </>
  );
};

export default App;