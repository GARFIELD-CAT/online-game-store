import { Row, Select } from "antd";

const MySelector = ({ title, defaultValue, options, onSelect }) => {
  const selectStyle = {
    width: 130,
    marginLeft: 10,
  };

  return (
    <Row
      style={{
        display: "flex",
        alignItems: "center",
        marginRight: 20,
        marginBottom: 10,
      }}
    >
      <p>{title}</p>
      <Select
        onSelect={(event) => {
          onSelect(event);
        }}
        style={selectStyle}
        defaultValue={defaultValue}
        options={options}
      ></Select>
    </Row>
  );
};

export default MySelector;
