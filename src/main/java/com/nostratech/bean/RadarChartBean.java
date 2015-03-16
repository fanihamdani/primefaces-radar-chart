package com.nostratech.bean;

import com.nostratech.model.QuarterlySales;
import com.nostratech.service.RadarChartService;
import com.nostratech.viewobject.VoRadarDataset;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fani on 2/5/15.
 */
@ManagedBean
@ViewScoped
public class RadarChartBean {

    private List<QuarterlySales> data;
    private RadarChartData radarChartData;

    private RadarChartService service;

    @PostConstruct
    public void init() {
        service = new RadarChartService();
    }

    public void search() {
        data = service.getData();
        prepareChartData(data);
    }

    public List<QuarterlySales> getData() {
        return data;
    }

    private void prepareChartData(List<QuarterlySales> radarData) {
        if (radarData != null && radarData.size() > 0) {
            VoRadarDataset.resetData();
            radarChartData = new RadarChartData();

            for (int i = 0; i < radarData.size(); ++i) {
                radarChartData.addLabel(radarData.get(i).getItem());

                VoRadarDataset.addDataQ1(radarData.get(i).getAmountQ1());
                VoRadarDataset.addDataQ2(radarData.get(i).getAmountQ2());
                VoRadarDataset.addDataQ3(radarData.get(i).getAmountQ3());
                VoRadarDataset.addDataQ4(radarData.get(i).getAmountQ4());
            }

            radarChartData.setDatasets(VoRadarDataset.get());
        }
    }

    public void prepareChartData() {
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        reqCtx.addCallbackParam("chartData", radarChartData);
    }

    public static class RadarChartData {
        /*
         * Tidak bisa langsung menggunakan String,
         * karena entah bagaimana RequestContext.addCallbackParam
         * akan gagal dalam proses konversi ke JSON (silently)
         */
//        private List<String> labels;
        private List<LabelWrapper> labels;
        private VoRadarDataset[] datasets;

        public RadarChartData() {
            labels = new ArrayList<LabelWrapper>();
        }

        public void addLabel(String label) {
            labels.add(new LabelWrapper(label));
        }

        public List<LabelWrapper> getLabels() {
            return labels;
        }

        public VoRadarDataset[] getDatasets() {
            return datasets;
        }

        public void setDatasets(VoRadarDataset[] datasets) {
            this.datasets = datasets;
        }

        public static class LabelWrapper {
            private byte[] label;

            public LabelWrapper(String label) {
                this.label = label.getBytes();
            }

            public byte[] getLabel() {
                return label;
            }
        }
    }
}
