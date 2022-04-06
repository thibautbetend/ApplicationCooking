<template>
  <div id="chartdiv">
  </div>
</template>

<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4maps from "@amcharts/amcharts4/maps";
// import am4themes_animated from "@amcharts/amcharts4/themes/animated"
import am4geodata_worldLow from "@amcharts/amcharts4-geodata/worldLow"
import am4themes_dataviz from "@amcharts/amcharts4/themes/dataviz";

am4core.useTheme(am4themes_dataviz)
export default {
    name: 'world',
    mounted() {
        let container = am4core.create("chartdiv", am4core.Container);
        container.width = am4core.percent(100);
        container.height = am4core.percent(100);
        // Create map instance
        var chart = container.createChild(am4maps.MapChart);

        var color = "#C74157"
        // Set map definition
        chart.geodata = am4geodata_worldLow;

        // Set projection
        chart.projection = new am4maps.projections.Miller();
        chart.maxZoomLevel = 1;
        chart.seriesContainer.draggable = false;
        chart.seriesContainer.resizable = false;
        chart.chartContainer.wheelable = false;

        // Create map polygon series
        var polygonSeries = chart.series.push(new am4maps.MapPolygonSeries());

        // Exclude Antartica
        polygonSeries.exclude = ["AQ"];

        // Make map load polygon (like country names) data from GeoJSON
        polygonSeries.useGeodata = true;

        // Configure series
        var polygonTemplate = polygonSeries.mapPolygons.template;
        // polygonTemplate.tooltipText = "{name}";
        polygonTemplate.polygon.fillOpacity = 0.8;
        polygonTemplate.polygon.fill = am4core.color("#fff");
        polygonTemplate.strokeWidth = 0;

        // Create hover state and set alternative fill color
        var hs = polygonTemplate.states.create("hover");
        hs.properties.fill = chart.colors.getIndex(0);


        // Add image series
        var imageSeries = chart.series.push(new am4maps.MapImageSeries());
        imageSeries.mapImages.template.propertyFields.longitude = "longitude";
        imageSeries.mapImages.template.propertyFields.latitude = "latitude";
        imageSeries.tooltip.getFillFromObject = false;
        imageSeries.tooltip.background.strokeWidth = 0;
        imageSeries.tooltip.background.cornerRadius = 20;
        imageSeries.tooltip.background.filters.clear();
        imageSeries.tooltip.background.fillOpacity = 0;
        imageSeries.mapImages.template.tooltipHTML = `
        <div class="tooltip">
            <div class="tooltip-box">
                <div class="pic-map" style="background-image:url('{img}')"></div>
                <div class="text-tooltip">
                    <div class="font-semibold flex">{recipe}</div>
                    <div class="text-sec text-xs">{title}</div>
                </div>
            </div>
        </div>
        `;
        imageSeries.mapImages.template.propertyFields.url = "url";
        imageSeries.mapImages.template.fill = "#fff"
        imageSeries.mapImages.template.fontFamily = "Poppins"
        imageSeries.mapImages.template.fontWeight = ""
        var circle = imageSeries.mapImages.template.createChild(am4core.Circle);
        circle.radius = 6;
        circle.propertyFields.fill = "color";

        var circle2 = imageSeries.mapImages.template.createChild(am4core.Circle);
        circle2.radius = 6;
        circle2.propertyFields.fill = "color";


        circle2.events.on("inited", function(event){
        animateBullet(event.target);
        })


        function animateBullet(circle) {
            var animation = circle.animate([{ property: "scale", from: 1, to: 4 }, { property: "opacity", from: 1, to: 0 }], 1000, am4core.ease.circleOut);
            animation.events.on("animationended", function(event){
            animateBullet(event.target.object);
            })
        }


        imageSeries.data = [{
        "title": "France",
        "recipe":"Stuffed tomato",
        "img": "https://cdn-elle.ladmedia.fr/var/plain_site/storage/images/elle-a-table/recettes-de-cuisine/tomate-farcie-comme-une-salade-de-lentilles-2976389/55818759-2-fre-FR/Tomate-farcie-comme-une-salade-de-lentilles.jpeg",
        "latitude": 48.8567,
        "longitude": 2.3510,
        "color":color
        }, {
        "title": "Island",
        "recipe":"Skyr",
        "img": "https://www.siggis-skyr.fr/wp-content/uploads/2015/08/mixed_berry_overnight_oats-300x200.jpg",
        "latitude": 64.1353,
        "longitude": -21.8952,
        "color":color
        },  {
        "title": "Spain",
        "recipe":"Tortilla",
        "img": "https://images.immediate.co.uk/production/volatile/sites/30/2013/05/tortilla-recipe-tapas-collection-4986f7f.jpg",
        "latitude": 40.4167,
        "longitude": -3.7033,
        "color":color
        }, {
        "title": "China",
        "recipe":"Chow Mein",
        "img":"https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chow-mein-c48a006.jpg",
        "latitude": 39.9056,
        "longitude": 116.3958,
        "color":color
        }, {
        "title": "India",
        "recipe":"Naan",
        "img": "https://rasamalaysia.com/wp-content/uploads/2019/03/naan2.jpg",
        "latitude": 28.6353,
        "longitude": 77.2250,
        "color":color
        }, {
        "title": "Japan",
        "recipe":"Ramen",
        "img":"https://d2rdhxfof4qmbb.cloudfront.net/wp-content/uploads/20180323163421/Ramen.jpg",
        "latitude": 35.6785,
        "longitude": 139.6823,
        "color":color
        },{
        "title": "Brasil",
        "recipe":"Farofa",
        "img":"https://img.theculturetrip.com/1440x/smart/wp-content/uploads/2020/02/f5gn23.jpg",
        "latitude": -15.7801,
        "longitude": -47.9292,
        "color":color
        },  {
        "title": "USA",
        "recipe":"Hot dog",
        "img": "https://cdn.theculturetrip.com/wp-content/uploads/2017/05/nathans.jpg",
        "latitude": 38.8921,
        "longitude": -77.0241,
        "color":color
        }];

    }
}
</script>

<style>
#chartdiv {
    @apply w-full h-full;
}
g[aria-label="Chart created using amCharts library"] {
@apply hidden;
}
.tooltip {
    @apply p-3;
}
.tooltip-box {
    @apply bg-white w-52 h-20 shadow-md rounded-2xl flex items-center flex-row ;
}
.text-tooltip {
    @apply flex justify-center flex-col text-main text-sm ml-2 w-full overflow-hidden;
}
.pic-map  {
    @apply w-16 h-16 flex-shrink-0 rounded-xl ml-2;
    background-size:cover;
    background-position:center;
}
</style>