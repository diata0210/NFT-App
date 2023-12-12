package models;

import java.util.HashMap;
import java.util.Map;

public class NiftyGateWayApiCallModel extends ApiModel implements CustomModel {
    public NiftyGateWayApiCallModel(int id, int niftyType, String creatorName, String nftyTitle) {
        super.id = id;
        super.niftyType = niftyType;
        super.creatorName = creatorName;
        super.niftyTitle = nftyTitle;
    }

    public NiftyGateWayApiCallModel() {
        super.creatorName = null;
        super.niftyTitle = null;
    }

    @Override
    public Map<String, Object> MapDescription() {
        Map<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("niftyType", niftyType);
        res.put("creatorName", creatorName);
        res.put("creatorName", niftyTitle);
        return res;
    }
}
