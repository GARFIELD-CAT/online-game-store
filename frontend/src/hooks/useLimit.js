import { useState, useEffect } from "react";

const useLimit = () => {
  const [limit, setLimit] = useState(16);
  const [countOfAdd, setCountOfAdd] = useState(3);
  const [width, setWidth] = useState(window.innerWidth);

  useEffect(() => {
    if (width > 1599) {
      setLimit(16);
      setCountOfAdd(4);
    } else if (width > 991) {
      setLimit(12);
      setCountOfAdd(3);
    } else if (width > 575) {
      setLimit(10);
      setCountOfAdd(2);
    } else if (width > 320) {
      setLimit(5);
      setCountOfAdd(2);
    }

    const handleResize = () => {
      setTimeout(() => {
        setWidth(window.innerWidth);
      }, 1000);
    };

    window.addEventListener("resize", handleResize);
  }, [width]);

  return {
    limit,
    setLimit,
    countOfAdd,
  };
};

export default useLimit;
