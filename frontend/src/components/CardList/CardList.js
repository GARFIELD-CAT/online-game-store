import { Card, List, Descriptions, Space, Checkbox } from "antd";
import Title from "antd/es/typography/Title";
import { Link } from "react-router-dom";
import ErrorMessage from "../ErrorMessage/ErrorMessage";
import { formateDate } from "../../utils/formatDate";

const CardList = ({ data, error }) => {
  if (error) {
    return <ErrorMessage />;
  }

  return (
    <Space direction="vertical">
      <List
        grid={{
          gutter: 16,
          xs: 1,
          sm: 2,
          md: 2,
          lg: 3,
          xl: 3,
          xxl: 4,
        }}
        dataSource={data}
        renderItem={(item) => (
          <List.Item
            key={item.id}
            style={{display: "flex"}}
           >
            <Checkbox style={{marginRight: "20px"}} value={item.id}/>
              <Card
                bodyStyle={{ padding: "10px 20px" }}
                style={{ background: "#00213A", border: "none" }}
                cover={<img alt={item.title} src={item.thumbnail} />}
              >
                <Title
                  level={4}
                  style={{ color: "#A8A8A8", textAlign: "start", margin: 0 }}
                >
                  {item.title}
                </Title>
                <Descriptions
                  layout="vertical"
                  column={2}
                  contentStyle={{ color: "#DCDCDC", fontSize: 12 }}
                  labelStyle={{ color: "#2B74AC", fontSize: 13 }}
                >
                  <Descriptions.Item
                    label="Genre"
                    style={{ padding: 0, height: 10 }}
                  >
                    {item.genre}
                  </Descriptions.Item>
                  <Descriptions.Item
                    label="Release Date"
                    style={{ padding: 0, height: 10 }}
                  >
                    {formateDate(item.release_date)}
                  </Descriptions.Item>
                  <Descriptions.Item
                    label="Publisher"
                    style={{ padding: 0, height: 10, margin: 5 }}
                  >
                    {item.publisher}
                  </Descriptions.Item>
                </Descriptions>
              </Card>
          </List.Item>
        )}
      />
    </Space>
  );
};

export default CardList;
