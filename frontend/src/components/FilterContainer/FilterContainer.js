import { Row } from "antd";
import { useDispatch } from "react-redux";
import { setGenre, setPlatform, setSort } from "../../store/gameReducer";
import {
  platformOptions,
  genreOptions,
  sortOptions,
} from "../../utils/filterOptions";
import MySelector from "../MySelector/MySelector";

const FilterContainer = () => {
  const dispatch = useDispatch();

  function changePlatform(event) {
    dispatch(setPlatform(event));
  }

  function changeGenre(event) {
    dispatch(setGenre(event));
  }

  function changeSort(event) {
    dispatch(setSort(event));
  }

  return (
    <Row
      style={{
        margin: "20px 0 10px 0",
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-between",
      }}
    >
      <MySelector
        title="Platform"
        defaultValue="All Platforms"
        options={platformOptions}
        onSelect={changePlatform}
      />
      <MySelector
        title="Genre:"
        defaultValue="All Genres"
        options={genreOptions}
        onSelect={changeGenre}
      />
      <MySelector
        title="Sort by:"
        defaultValue="Relevance"
        options={sortOptions}
        onSelect={changeSort}
      />
    </Row>
  );
};

export default FilterContainer;
