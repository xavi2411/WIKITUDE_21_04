var Solar = {
    planetsInfo: null,

    init: function() {
        var distanceFactor = 580.2;

        var estrada = new AR.GeoLocation(42.392147, 2.867847);
        var locationSun = new AR.RelativeLocation(null, 25000, -25000, 5000);

        var sizeFactor = 0.5;
        var sizeEarth = 12.8 * 25;

        var sunImg = new AR.ImageResource("assets/Sun-icon.png");
        var indicatorImg = new AR.ImageResource("assets/indi.png");

        var sunSize = (((25 * sizeEarth) / sizeEarth) * 0.3) * sizeFactor;

        var sun = {
            name: "Sun",
            distance: 0,
            location: estrada,
            imgDrawable: new AR.ImageDrawable(sunImg, sunSize),
            size: sunSize,
            description: "The Sun is the star at the center of the Solar System. It is almost perfectly spherical and consists of hot plasma interwoven with magnetic fields.",
            mass: "2&nbsp;10<sup>30</sup>&nbsp;kg",
            diameter: "1,392,684&nbsp;km"
        };

        var label = new AR.Label(sun.name, 3, {
            offsetY: -sun.size / 2,
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP,
            opacity: 0.9,
            zOrder: 1,
            style: {
                textColor: '#FFFFFF',
                backgroundColor: '#00000005'
            }
        });

        this.planetsInfo = [sun];

        var drawables = [];
        drawables[0] = new AR.ImageDrawable(sunImg, sunSize);
        drawables[1] = label;

        var sol = new AR.GeoObject(estrada, {
            drawables: {
                cam: drawables
            }
        });

        var imageDrawable = new AR.ImageDrawable(indicatorImg, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        });

        sol.drawables.addIndicatorDrawable(imageDrawable);
    },

    locationChanged: function locationChangedFn(lat, lon, alt, acc) {
        //TODO
    },
};

AR.context.onLocationChanged = Solar.locationChanged;
AR.context.scene.cullingDistance = 1000000;

Solar.init();
//Solar.locationChanged(42.392043, 2.867616, null, null);