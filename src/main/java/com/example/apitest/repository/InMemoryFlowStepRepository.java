package com.example.apitest.repository;

import com.example.apitest.entity.FlowStep;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryFlowStepRepository {

    private final AtomicLong idGen = new AtomicLong(1);
    private final Map<Long, FlowStep> data = new ConcurrentHashMap<>();

    public FlowStep save(FlowStep step) {
        if (step.getId() == null) {
            step.setId(idGen.getAndIncrement());
        }
        data.put(step.getId(), step);
        return step;
    }

    public List<FlowStep> findByDraftId(Long draftId) {
        List<FlowStep> list = new ArrayList<>();
        for (FlowStep step : data.values()) {
            if (draftId.equals(step.getDraftId()) && (step.getDeleted() == null || step.getDeleted() == 0)) {
                list.add(step);
            }
        }
        list.sort(Comparator.comparing(FlowStep::getStepOrder));
        return list;
    }

    public void deleteByDraftId(Long draftId) {
        for (FlowStep step : data.values()) {
            if (draftId.equals(step.getDraftId())) {
                step.setDeleted(1);
            }
        }
    }
}
