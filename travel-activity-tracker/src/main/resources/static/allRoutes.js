    // BiH - Mostar
    var origin = [17.80806, 43.34333];
    var features = [];

    //A simple lines from origin to destination.
    for (var i = 0; i < endLong.length; i++) {
        features.push(turf.lineString([new Array(startLong[i], startLat[i]), new Array(endLong[i], endLat[i])], {
            name: 'line1'
        }));
    }
    var route = turf.featureCollection(features);


    // A single point that animates along the route.
    // Coordinates are initially set to origin.
    var point = {
        "type": "FeatureCollection",
        "features": [{
            "type": "Feature",
            "properties": {},
            "geometry": {
                "type": "Point",
                "coordinates": origin
            }

        }]
    };

    // Number of steps to use in the arc and animation, more steps means
    // a smoother arc and animation, but too many steps will result in a
    // low frame rate
    var steps = 100;

    // Calculate the distance in kilometers between route start/end point.
    for (var i = 0; i < endLong.length; i++) {
        var lineDistance = turf.length(turf.lineString([new Array(startLong[i], startLat[i]), new Array(endLong[i], endLat[i])], {
            name: 'line'
        }), {
            units: 'kilometers'
        });
        var arc = [];
        // Draw an arc between the `origin` & `destination` of the two points
        for (var j = 0; j < lineDistance; j += lineDistance / steps) {
            var segment = turf.along(route.features[i], j, {
                units: 'kilometers'
            });
            arc.push(segment.geometry.coordinates);
        }
        // Update the route with calculated arc coordinates
        route.features[i].geometry.coordinates = arc;
    }

    map.on('load', function() {

        // Used to increment the value of the point measurement against the route.
        var counter = 0;
        var plane = -1; //because of incrementing in animate function

        // Add a source and layer displaying a point which will be animated in a circle.
        map.addSource('route', {
            "type": "geojson",
            "data": route
        });

        map.addSource('point', {
            "type": "geojson",
            "data": point
        });

        map.addLayer({
            "id": "route",
            "source": "route",
            "type": "line",
            "paint": {
                "line-width": 2,
                "line-opacity": .8,
                "line-color": "#00abff"
            }
        });

        map.addLayer({
            "id": "point",
            "source": "point",
            "type": "symbol",
            "layout": {
                "icon-image": "airport-15",
                "icon-rotate": ["get", "bearing"],
                "icon-rotation-alignment": "map",
                "icon-allow-overlap": true
            }
        });


        function animate() {
            // Update point geometry to a new position based on counter denoting
            // the index to access the arc.
            point.features[0].geometry.coordinates = route.features[plane].geometry.coordinates[counter];
            // Calculate the bearing to ensure the icon is rotated to match the route arc
            // The bearing is calculate between the current point and the next point, except
            // at the end of the arc use the previous point and the current point
            point.features[0].properties.bearing = turf.bearing(
                turf.point(route.features[plane].geometry.coordinates[counter >= steps ? counter - 1 : counter]),
                turf.point(route.features[plane].geometry.coordinates[counter >= steps ? counter : counter + 1])
            );

            // Update the source with this new data.
            map.getSource('point').setData(point);


            // the index to access the arc.


            // Request the next frame of animation so long the end has not been reached.
            if (counter < steps) {
                requestAnimationFrame(animate);
            }

            counter = counter + 1;
        }


        window.setInterval(function() {

            plane = plane + 1;
            if (plane < endLat.length) {
                point.features[0].geometry.coordinates = route.features[plane].geometry.coordinates[counter];

                // Update the source layer
                map.getSource('point').setData(point);

                // Reset the counter
                counter = 0;

                // Start the animation.
                animate(counter);
            }
        }, 10000);


    });