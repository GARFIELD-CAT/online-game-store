import { Carousel } from "antd";

const ScreenShotsCarousel = ({ currentGame }) => {
  const screenShotsArr = currentGame.screenshots;
  return (
    <Carousel
      style={{
        maxWidth: "80vw",
        margin: "10px auto",
      }}
      autoplay
    >
      {screenShotsArr?.map((screenShot) => {
        return (
          <div key={screenShot.id}>
            <img
              style={{
                width: "100%",
              }}
              src={screenShot.image}
              alt="game screenshot"
            />
          </div>
        );
      })}
    </Carousel>
  );
};
export default ScreenShotsCarousel;
