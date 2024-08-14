package ca.awoo.jray;

import java.util.HashSet;
import java.util.Set;

public class Scene {
    //TODO: perhaps an octree would cause less dispair?
    private final Set<Solid> solids = new HashSet<>();

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public Colour castColour(Ray ray){
        RayHit nearestHit = null;
        Solid nearestSolid = null;
        for(Solid solid : solids){
            RayHit[] hits = solid.shape().hit(ray);
            for(RayHit hit : hits){
                //Check if hit is in front of us and facing us
                if(hit.distance() > 0 && hit.normal().dot(ray.direction()) < 0){
                    if(nearestHit == null){
                        //First valid hit we found
                        nearestHit = hit;
                        nearestSolid = solid;
                    }else{
                        if(hit.distance() < nearestHit.distance()){
                            nearestHit = hit;
                            nearestSolid = solid;
                        }
                    }
                }
            }
        }
        if(nearestHit == null){
            //We hit nothing
            return new Colour(0, 0, 0);
        }else{
            return nearestSolid.material().colour(nearestSolid.shape(), nearestHit, this);
        }
    }
}
