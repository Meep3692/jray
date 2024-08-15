package ca.awoo.jray;

import java.util.HashSet;
import java.util.Set;

public class Scene {
    //TODO: perhaps an octree would cause less dispair?
    private final Set<Solid> solids = new HashSet<>();
    private final int maxDepth = 10;

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public Colour castColour(Ray ray){
        return castColour(ray, null);
    }

    public Colour castColour(Ray ray, Solid exclude){
        if(ray.depth() > maxDepth) return new Colour(0, 0, 0);
        RayHit nearestHit = null;
        Solid nearestSolid = null;
        for(Solid solid : solids){
            if(solid.equals(exclude)){
                continue;
            }
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
            return nearestSolid.material().colour(nearestSolid, nearestHit, this);
        }
    }
}
