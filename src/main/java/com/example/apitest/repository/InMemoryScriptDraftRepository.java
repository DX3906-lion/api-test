package com.example.apitest.repository;

import com.example.apitest.entity.ScriptDraft;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryScriptDraftRepository {

    private final AtomicLong idGen = new AtomicLong(1);
    private final Map<Long, ScriptDraft> data = new ConcurrentHashMap<>();

    public ScriptDraft save(ScriptDraft draft) {
        if (draft.getId() == null) {
            draft.setId(idGen.getAndIncrement());
        }
        data.put(draft.getId(), draft);
        return draft;
    }

    public ScriptDraft findById(Long id) {
        return data.get(id);
    }

    public List<ScriptDraft> findByWorkspaceId(Long workspaceId) {
        List<ScriptDraft> list = new ArrayList<>();
        for (ScriptDraft d : data.values()) {
            if (workspaceId.equals(d.getWorkspaceId()) && (d.getDeleted() == null || d.getDeleted() == 0)) {
                list.add(d);
            }
        }
        list.sort(Comparator.comparing(ScriptDraft::getId).reversed());
        return list;
    }

    public ScriptDraft findByWorkspaceIdAndDraftName(Long workspaceId, String draftName) {
        for (ScriptDraft d : data.values()) {
            if (workspaceId.equals(d.getWorkspaceId()) && draftName.equals(d.getDraftName())
                    && (d.getDeleted() == null || d.getDeleted() == 0)) {
                return d;
            }
        }
        return null;
    }
}
