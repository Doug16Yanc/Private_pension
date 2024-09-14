package douglas.service;

import douglas.domain.entity.Plan;
import douglas.exception.plans.PlanNotFoundException;
import douglas.repository.PlanRepository;
import jakarta.enterprise.context.ApplicationScoped;


import java.util.List;

@ApplicationScoped
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan create(Plan plan) {
        planRepository.persist(plan);
        return plan;
    }

    public List<Plan> findAll(Integer page, Integer size) {
        return planRepository.findAll()
                .page(page, size)
                .list();
    }

    public Plan findById(Long id) {
        return planRepository.findByIdOptional(id)
                .orElseThrow(PlanNotFoundException::new);
    }

    public void deleteById(Long id) {
        Plan plan = findById(id);
        planRepository.delete(plan);
    }
}
